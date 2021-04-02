package shop

import common.StarvConfig
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.DateTime
import udf.UDFRegister
import utils.BroadcastUtils

object ClintAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DealAnlysis")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()

    val dt = args(0)
    UDFRegister.clientMapping(spark, dt)


    spark.sql(
      """
        |select
        |*
        |from
        |dwd.dw_orders_merge_detail
        |""".stripMargin).createOrReplaceTempView("orders_merge_detail")
    spark.sqlContext.cacheTable("orders_merge_detail")

    /**
     * 客户数-- 店铺维度:
     * 筛选时间内，付款成功的客户数，一人多次付款成功记为一人
     * 新成交客户数：过去1年没有购买，在筛选时间内首次在店铺付款的客户数量
     * 老成交客户数：过去1年购买过，在筛选时间内在店铺再次付款的客户数量
     * and
     * 客户数占比：全部成交客户占比：筛选时间成交客户数 / 累积所有客户数
     * 新成交客户占比：筛选时间新成交客户数 / 全部成交客户数
     * 老成交客户占比：筛选时间老成交客户数 / 全部成交客户数
     */
    spark.sql(
      """
        |select
        |shop_id, --店铺ID
        |order_id, -- 订单号
        |buyer_id, -- 买方用户ID
        |create_time, -- 创建时间
        |case when order_type = 1 then "TC" else "TB" end as order_type,--区分TC TB 1为TC 其他为TB
        |case when DATEDIFF(CURRENT_TIMESTAMP, create_time) <= 360
        |then 1 else 0 end as flag_years, --满足返回1为1年内购买过的,不满足返回0为1年内没购买的
        |case when DATEDIFF(CURRENT_TIMESTAMP, create_time) = 0
        |then 1 else 0 end present_day -- 判断是否是当天订单
        |from
        |dwd.fact_orders a
        |where end_zipper_time ='9999-12-31'
        |and paid = 2 and shop_id is not null
        |""".stripMargin).createOrReplaceTempView("order_tmp")
    //分平台
    spark.sql(
      """
        |select
        |shop_id,
        |order_type,
        |count(distinct buyer_id)  user_dis_number, --累计所有的成交用户数
        |count(distinct case when present_day = 1 then buyer_id end) as
        |present_user_dis_number, --当天成交的用户数
        |count(distinct case when flag_years = 1 and present_day = 1
        |then buyer_id end) as aged_user_dis_number, -- 当天成交的老用户数
        |count(distinct case when  flag_years = 1 and present_day = 1
        |then buyer_id end) as new_user_dis_number -- 当天成交的新用户数
        |from
        |order_tmp
        |-- where present_day = 1
        |group by shop_id,order_type
        |""".stripMargin).createOrReplaceTempView("client_order_type")
    //全平台
    spark.sql(
      """
        |select
        |shop_id,
        |count(distinct buyer_id)  user_dis_number, --累计所有的成交用户数
        |count(distinct case when present_day = 1 then buyer_id end) as
        |present_user_dis_number, --当天成交的用户数
        |count(distinct case when flag_years = 1 and present_day = 1
        |then buyer_id end) as aged_user_dis_number, -- 当天成交的老用户数
        |count(distinct case when  flag_years = 1 and present_day = 1
        |then buyer_id end) as new_user_dis_number -- 当天成交的新用户数
        |from
        |order_tmp
        |-- where present_day = 1
        |group by shop_id
        |""".stripMargin).createOrReplaceTempView("client_order_tmp")

    spark.sql(
      s"""
        |select
        |shop_id,
        |order_type,
        |user_dis_number,
        |present_user_dis_number,
        |aged_user_dis_number,
        |new_user_dis_number,
        |round(present_user_dis_number/user_dis_number,2) as type_user_ratio,
        |round(new_user_dis_number/user_dis_number,2) as new_user_ratio,
        |round(aged_user_dis_number/user_dis_number,2) as aged_user_ratio,
        |$dt as dt
        |from
        |client_order_type
        |""".stripMargin).union(spark.sql(s"""
         |select
         |shop_id,
         |'all' as order_type,
         |user_dis_number,
         |present_user_dis_number,
         |aged_user_dis_number,
         |new_user_dis_number,
         |round(present_user_dis_number/user_dis_number,2) as type_user_ratio,
         |round(new_user_dis_number/user_dis_number,2) as new_user_ratio,
         |round(aged_user_dis_number/user_dis_number,2) as aged_user_ratio,
         |$dt as dt
         |from
         |client_order_tmp
         |""".stripMargin))
//      .write
//      .mode(SaveMode.Append)
//      .jdbc(properties.get("url").toString(), "shop_client_analysis", properties)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"shop_client_analysis",StarvConfig.properties)

    /**
     *
     * 访问-支付转化率 -- 需埋点
     * * 全部成交客户-访问-支付转化率：全部支付成功客户数/店铺访客数
     * 新成交客户-访问-支付转化率： 新成交客户数/店铺访客数中近1年无购买记录的访客数
     * 老成交客户-访问-支付转化率：老成交客户数/店铺访客数中近1年购买过的访客数
     */


    /**
     * 统计分平台客户采购额，按购买次后金额排名统计
     */
    spark.sql(
      s"""
         |with t1 as(
         |select
         |shop_id,
         |order_type,
         |buyer_id,
         |payment_total_money,
         |payment_total_money - (num * cost_price) as profit
         |from
         |orders_merge_detail
         |where paid = 2 and refund = 0
         |),
         |t2 as(
         |select
         |shop_id,
         |order_type,
         |buyer_id,
         |case when round(sum(profit),2)  is null
         |then 0 else round(sum(profit),2) end as sale_succeed_profit,
         |case when round(sum(payment_total_money),2) is null
         |then 0 else round(sum(payment_total_money),2)
         |end as sale_succeed_money
         |from
         |t1
         |group by shop_id,order_type,buyer_id
         |),t3 as (
         |select
         |shop_id,
         |order_type,
         |user_mapping(buyer_id) as user_name,
         |sale_succeed_money,
         |sale_succeed_profit,
         |row_number() over(partition by shop_id,order_type,buyer_id order by sale_succeed_money desc) as profit_top
         |from
         |t2
         |)
         |select
         |shop_id,
         |order_type,
         |user_name,
         |sale_succeed_money,
         |sale_succeed_profit,
         |$dt as dt
         |from
         |t3
         |where profit_top <=10
         |""".stripMargin).union(spark.sql(s"""
         |with t1 as(
         |select
         |shop_id,
         |buyer_id,
         |payment_total_money,
         |payment_total_money - (num * cost_price) as profit
         |from
         |orders_merge_detail
         |where paid = 2 and refund = 0
         |),
         |t2 as(
         |select
         |shop_id,
         |buyer_id,
         |case when round(sum(profit),2)  is null
         |then 0 else round(sum(profit),2) end as sale_succeed_profit,
         |case when round(sum(payment_total_money),2) is null
         |then 0 else round(sum(payment_total_money),2)
         |end as sale_succeed_money
         |from
         |t1
         |group by shop_id,buyer_id
         |),t3 as (
         |select
         |shop_id,
         |user_mapping(buyer_id) as user_name,
         |sale_succeed_money,
         |sale_succeed_profit,
         |row_number() over(partition by shop_id,buyer_id order by sale_succeed_money desc) as profit_top
         |from
         |t2
         |)
         |select
         |shop_id,
         |'all' as order_type,
         |user_name,
         |sale_succeed_money,
         |sale_succeed_profit,
         |$dt as dt
         |from
         |t3
         |where profit_top <=10
         |""".stripMargin))
//      .write
//      .mode(SaveMode.Append)
//      .jdbc(properties.get("url").toString(), "shop_client_sale_top", properties)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"client_sale_top",StarvConfig.properties)

    /**
     * 分平台 省份排行
     */
    spark.sql(
      s"""
         |with t1 as(
         |select
         |shop_id,
         |order_type,
         |province_name,
         |count(distinct buyer_id) as sale_user_count,
         |round(sum(payment_total_money),2) as sale_succeed_money
         |from
         |orders_merge_detail
         |where paid = 2 and refund = 0
         |group by shop_id,order_type,province_name
         |),
         |t2 as (select
         |shop_id,
         |order_type,
         |province_name,
         |sale_user_count,
         |sale_succeed_money,
         |sum(sale_succeed_money) over(partition by shop_id) as total_province_money,
         |row_number() over(partition by shop_id,order_type,province_name order by sale_succeed_money desc) as money_top
         |from
         |t1
         |)
         |select
         |shop_id,
         |order_type,
         |province_name,
         |sale_user_count,
         |sale_succeed_money,
         |round(sale_succeed_money/total_province_money,2) as sale_ratio,
         |$dt as dt
         |from
         |t2
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_province_type_top",StarvConfig.properties)




  }
}
