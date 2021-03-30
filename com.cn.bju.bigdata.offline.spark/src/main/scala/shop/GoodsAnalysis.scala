package shop

import common.StarvConfig
import org.apache.commons.lang3.time.{DateFormatUtils, DateUtils}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.{DateTime, LocalDate}
import java.util.Properties

import udf.UDFRegister
/**
 * @author ljh
 * @date 2021/3/23 14:29
 * @version 1.0
 * 商品分析
 */
object GoodsAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Goods_info")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()
    val dt = args(0)
    spark.sqlContext.cacheTable("dwd.dw_orders_merge_detail")
    UDFRegister.shopMapping(spark,dt)



    //统计在架商品数据
//    spark.sql(
//      s"""
//        |select
//        |shop_id,
//        |count(1) as item_number,
//        |$dt as dt
//        |from dwd.fact_item
//        |where status = 4 and end_zipper_time = '9999-12-31'
//        |group by shop_id
//        |""".stripMargin)

      spark.sql(
        """
          |select
          |shop_id,
          |shop_mapping(shop_id) as shop_name,
          |count(distinct case when status = 4 then  item_id end) as item_number
          |from
          |ods.ods_item
          |-- where dt = $dt
          |group by shop_id
        """.stripMargin).createOrReplaceTempView("shelf_goods_shelves")



//      .write
//      .mode(SaveMode.Append)
//      .jdbc(StarvConfig.url,"goods_shelves",StarvConfig.properties)

    //解析hdfs_page
//    spark.sql(
//      """
//        |select
//        |url,
//        |refer,
//        |finger_print,
//        |user_cookies,
//        |from
//        |bjubigdata_hdfs_db.ods_start_log
//        |where url is not null  and to_date(time_in) = '$dt'
//        |""".stripMargin).createOrReplaceTempView("hdfs_page")

    /**
     * 被访问商品品种量
     * 1.从url中解析出：sku_id,和item_id
     * 2. 统计item_id下每个sku_id个数
     */
//    spark.sql(
//      s"""
//         |with t1 as(
//         |SELECT
//         |item_id,
//         |sku_id
//         |from  hdfs_page
//         |GROUP BY item_id,sku_id
//         |),
//         |select
//         |item_id,
//         |count(1) as sku_pv
//         |from t1
//         |GROUP BY item_id
//         |""".stripMargin).createOrReplaceTempView("goods_pv")
    //
    /**
     *动销商品数 = 有成交的商品的品种数，
     *成交订单为当日付款当日未取消的在线支付订单，
     *或者当日下单当日未取消的货到付款订单。
     *下单客户数
     *下单笔数
     *下单商品件数
     *成交商品件数
     * 成交金额
     *商品成交转化率 --需要埋点
     */
    spark.sql(
      s"""
         |select
         |shop_id,
         |shop_mapping(shop_id) as shop_name,
         |count(distinct case when paid = 2 then sku_id end) as sale_number,
         |count(distinct buyer_id) as sale_user_number,
         |count(distinct order_id) as sale_number,
         |round(sum(case when num is null then 0 else num end ),2) as sale_goods_number,
         |round(sum(case when paid = 2 and refund = 0 then num end),2) as sale_succeed_number,
         |round(sum(case when paid = 2 and refund = 0 then payment_total_money end),2) as sale_succeed_money,
         |$dt as dt
         |from
         |dwd.dw_orders_merge_detail
         |group by shop_id
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)

//    spark.sql(
//      s"""
//         |SELECT
//         |order_id,
//         |seller_id,
//         |from
//         |trade_order_items
//         |WHERE
//         |to_date(create_time) = '$dt'
//         |""".stripMargin).createOrReplaceTempView("order_items")
//    spark.sql(
//      """
//        |with t1 as (select
//        |a.shop_id,
//        |b.sku_id
//        |from
//        |orders a
//        |left join
//        |order_items b
//        |on a.order_id = b.order_id
//        |where b.sku_id is not null
//        |)
//        |select shop_id,count(1) as sale_kind_number from t1 group by  shop_id,sku_id
//        |""".stripMargin)

    /**
     * 商品曝光率 --需要埋点
     * 有流量的商品的品种数占
     * 上架商品数量的比例
     */
//    spark.sql(
//      s"""
//         |with t1 as (select
//         |seller_id,
//         |count(1) as on_sale_number,
//         |$dt
//         |from item
//         |where item_status != 5
//         |group by seller_id
//         |)
//         |select
//         |b.seller_id
//         |a.sku_pv/b.on_sale_number
//         |from goods_pv a
//         |left join
//         |t1 b
//         |on
//         |a.seller_id = b.seller_id
//         |""".stripMargin)


    /**
     * 商品浏览量
     */
//    spark.sql(
//      s"""
//         |SELECT
//         |item_id,
//         |count(1) shop_pv
//         |from  hdfs_page
//         |GROUP BY item_id,sku_id
//         |""".stripMargin)

    /**
     * 商品关注数
     *
     */
    /**
     * 加购商品数
     */
    /**
     * 加购商品件数
     */


    /**
     *
     * 下单客户数
     */
//    spark.sql(
//      s"""
//         |with t1 as(
//         |select
//         |shop_id,
//         |buyer_id
//         |from
//         |dwd.dw_orders_merge_detail
//         |group by shop_id,buyer_id
//         |)
//         |select
//         |shop_id,
//         |count(1) as sale_user_count,
//         |$dt as dt
//         |from t1
//         |group by shop_id
//         |""".stripMargin)
//      .write
//      .mode(SaveMode.Append)
//      .jdbc(StarvConfig.url,"place_orders_user",StarvConfig.properties)

//    /**
//     * 下单商品件数
//     */
//    spark.sql(
//      s"""
//         |select
//         |shop_id,
//         |round(sum(case when num is null then 0 else num end ),2) as sale_goods_number,
//         |$dt as dt
//         |from
//         |dwd.dw_orders_merge_detail
//         |group by shop_id
//         |""".stripMargin)
//         .write
//         .mode(SaveMode.Append)
//         .jdbc(StarvConfig.url,"place_orders_goods",StarvConfig.properties)
    /**
     * 成交商品件数
     */
//    spark.sql(
//      s"""
//         |select
//         |shop_id,
//         |round(sum(num),2) as sale_succeed_number,
//         |$dt as dt
//         |from
//         |dwd.dw_orders_merge_detail
//         |where paid = 2 and refund = 0
//         |group by shop_id
//         |""".stripMargin)
//        .write
//        .mode(SaveMode.Append)
//        .jdbc(StarvConfig.url,"trading_goods",StarvConfig.properties)


    /**
     * 商品成交转化率   --需要埋点
     * 1.统计出商品访问数
     * 2.统计出成交客户数
     * 3.成交客户数/商品访问数
     */
//    spark.sql(
//      """
//        |with
//        |succeed_user as(
//        |select
//        |shop_id,
//        |count(distinct buyer_id) as sale_user_count
//        |from
//        |dwd.dw_orders_merge_detail
//        |where paid = 2 and refund = 0
//        |group by shop_id
//        |),
//        |shop_user_visit as (
//        |SELECT
//        |item_id,
//        |count(1) shop_user_uv
//        |from  hdfs_page
//        |GROUP BY item_id
//        |),
//        |select
//        |a.shop_id,
//        |case when b.shop_user_uv is null
//        |then 0 else
//        |a.sale_user_count/b.shop_user_uv
//        |end as sku_rate
//        |from
//        |succeed_user a
//        |left join
//        |shop_user_visit b
//        |on a.shop_id = b.item_id
//        |""".stripMargin)

    /**
     * 商品金额TOP 10
     * 1.统计支付人数
     * 2.统计支付金额
     * 3.统计支付金额占总支付金额比例
     */
    spark.sql(
      s"""
        |with t1 as(
        |select
        |shop_id,
        |item_name,
        |count(distinct buyer_id) as sale_user_count,
        |round(sum(payment_total_money),2) as sale_succeed_money
        |from
        |dwd.dw_orders_merge_detail
        |where paid = 2 and refund = 0
        |group by shop_id,item_name
        |),
        |t2 as (
        |select
        |shop_id,
        |item_name,
        |sale_user_count,
        |sale_succeed_money,
        |sum(sale_succeed_money) over(partition by shop_id) as total_money,
        |row_number() over(partition by shop_id,item_name order by sale_succeed_money desc) as money_top
        |from
        |t1
        |)
        |select
        |shop_id,
        |item_name,
        |sale_user_count,
        |sale_succeed_money,
        |round(sale_succeed_money/total_money,2) as sale_ratio,
        |$dt as dt
        |from
        |t2
        |where money_top <=10
        |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_money_top",StarvConfig.properties)

    /**
     * 商品利润TOP 10
     * 1.先计出每个订单的成本价
     * 2.统计每个商品总利润和访问人数
     * 3.统计商铺总利润和对每个商品的总利润排序
     * 4.取出利润最高的10个商品，并计算利润率(单个商品总利润/店铺总利润)
     */
    spark.sql(
      s"""
        |with t1 as(
        |select
        |shop_id,
        |item_name,
        |buyer_id,
        |payment_total_money - (num * cost_price) as profit
        |from
        |dwd.dw_orders_merge_detail
        |where paid = 2 and refund = 0
        |),
        |t2 as(
        |select
        |shop_id,
        |item_name,
        |count(distinct buyer_id) as sale_user_count,
        |round(sum(profit),2) as sale_succeed_profit
        |from
        |t1
        |group by shop_id,item_name
        |),t3 as (
        |select
        |shop_id,
        |item_name,
        |sale_user_count,
        |sale_succeed_profit,
        |sum(sale_succeed_profit) over(partition by shop_id) as total_profit,
        |row_number() over(partition by shop_id,item_name order by sale_succeed_profit desc) as profit_top
        |from
        |t2
        |)
        |select
        |shop_id,
        |item_name,
        |sale_user_count,
        |round(sale_succeed_profit,2) as sale_succeed_profit,
        |round(sale_succeed_profit/total_profit,2) as sale_profit_ratio,
        |$dt as dt
        |from
        |t3
        |where profit_top <=10
        |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_profit_top",StarvConfig.properties)

  }
}
