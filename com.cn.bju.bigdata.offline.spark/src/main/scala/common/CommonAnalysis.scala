package common
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.DateTime

import java.util.Properties

/**
 * @author ljh
 * @date 2021/3/23 14:29
 * @version 1.0
 * 公共指标
 */
object CommonAnalysis {


  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("CommonAnalysis")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()
    val dt = args(0)
    /**
     * 访客数
     */
//    spark.sql(
//      s"""
//         |witch t1 as(
//         |SELECT
//         |item_id,
//         |finger_print
//         |from  hdfs_page
//         |GROUP BY item_id,finger_print
//         |)
//         |select
//         |item_id,
//         |count(1) shop_user_uv
//         |from t1
//         |group by item_id
//         |""".stripMargin).createOrReplaceTempView("shop_user_visit")
    /**
     * 成交金额 =本店收款的支付金额  and 成交商品件数 and 成交单量
     * and 支付人数
     * ------------------------
     * 支付订单数，即成交单量：
     * 统计时间内(按天、周、月统计)用户付款的总订单量，
     * 包括先款订单量(在线支付、公司转账、邮局汇款等)和货到付款订单量。
     *
     * 增加 sale_user_number，sale_succeed_money，order_type
     */
    spark.sql(
      s"""
         |select
         |shop_id,
         |order_type,
         |round(sum(num),2) as sale_succeed_number,
         |count(distinct order_id) as succeed_orders_number,
         |count(distinct buyer_id) as sale_user_number,
         |round(sum(payment_total_money),2) as sale_succeed_money,
         |$dt as dt
         |from
         |dwd.dw_orders_merge_detail
         |where paid = 2 and refund = 0
         |group by shop_id,order_type
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"successful_transaction",StarvConfig.properties)
    /**
     * 客单价
     *  =成交金额/成交客户数。
     * 2.0
     * 新增维度
     * order_type 平台类型
     */
    spark.sql(
      s"""
         |select
         |shop_id,
         |order_type,
         |round(sum(payment_total_money),3) as sale_succeed_money
         |from
         |dwd.dw_orders_merge_detail
         |where paid = 2 and refund = 0
         |group by shop_id,order_type
         |""".stripMargin).createOrReplaceTempView("succeed")
    //单平台用户数
    spark.sql(
      s"""
         |with t1 as(
         |select
         |shop_id,
         |order_type,
         |buyer_id
         |from
         |dwd.dw_orders_merge_detail
         |where  paid = 2 and refund = 0
         |group by shop_id,order_type,buyer_id
         |)
         |select
         |shop_id,
         |order_type,
         |count(1) as sale_user_type_count
         |from t1
         |group by shop_id,order_type
         |""".stripMargin).createOrReplaceTempView("succeed_user_type")
    //全平台用户数
    spark.sql(
      s"""
         |with t1 as(
         |select
         |shop_id,
         |buyer_id
         |from
         |dwd.dw_orders_merge_detail
         |where  paid = 2 and refund = 0
         |group by shop_id,buyer_id
         |)
         |select
         |shop_id,
         |count(1) as sale_user_count
         |from t1
         |group by shop_id
         |""".stripMargin).createOrReplaceTempView("succeed_user")
    spark.sql(
      s"""
        |select
        |a.shop_id,
        |a.order_type,
        |c.sale_user_count, --全平台用户数
        |case when a.sale_succeed_money is null then 0 else a.sale_succeed_money
        |end as sale_succeed_money, --分平台金额
        |case when round(sale_succeed_money/sale_user_type_count,2) is not null
        |then round(sale_succeed_money/sale_user_type_count,2)
        |else 0 end as money,
        |$dt as dt
        |from
        |succeed a
        |left join
        |succeed_user_type b
        |on a.shop_id = b.shop_id and a.order_type = b.order_type
        |left join
        |succeed_user c
        |on a.shop_id = c.shop_id
        |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"client_one_price",StarvConfig.properties)



    /**
     * 商品省份TOP 10
     * 1.统计支付人数
     * 2.统计支付金额
     * 3.统计支付金额占总支付金额比例
     * 地势分布
     * 包含： 商家交易分析 模块
     */
    spark.sql(
      s"""
        |with t1 as(
        |select
        |shop_id,
        |province_name,
        |count(distinct buyer_id) as sale_user_count,
        |round(sum(payment_total_money),2) as sale_succeed_money
        |from
        |dwd.dw_orders_merge_detail
        |where paid = 2 and refund = 0
        |group by shop_id,province_name
        |),
        |t2 as (select
        |shop_id,
        |province_name,
        |sale_user_count,
        |sale_succeed_money,
        |sum(sale_succeed_money) over(partition by shop_id) as total_province_money
        |from
        |t1
        |)
        |select
        |shop_id,
        |province_name,
        |sale_user_count,
        |sale_succeed_money,
        |round(sale_succeed_money/total_province_money,2) as sale_ratio
        |$dt as dt
        |from
        |t2
        |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_province_top",StarvConfig.properties)

  }


}
