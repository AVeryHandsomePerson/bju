package shop

import common.StarvConfig
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.DateTime
import udf.UDFRegister

object OneGoodsAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("OneGoods_info")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()
    val dt = args(0)

    //注册udf
    UDFRegister.skuMapping(spark, dt)


    //商品和订单表关联
    //    spark.sql(
    //      s"""
    //         |select
    //         |a.shop_id,
    //         |b.paid
    //         |from
    //         |(
    //         |select
    //         |*
    //         |from ods.ods_item
    //         |) a
    //         |left join
    //         |(
    //         |select
    //         |* from
    //         |dwd.fact_orders
    //         |where parent_order_id != 0
    //         |-- and dt = $dt
    //         |) b
    //         |on a.shop_id = b.shop_id
    //         |""".stripMargin)
    //最新上架的前10商品数
    spark.sql(
      s"""
         |with t1 as (select
         |shop_id,
         |shelve_time,
         |item_id,
         |item_name,
         |row_number() over(partition by shop_id order by shelve_time desc) as profit_top
         |from
         |ods.ods_item
         |-- where dt = $dt
         |)
         |select
         |shop_id,
         |shelve_time,
         |item_id,
         |item_name,
         |$dt as dt
         |from
         |t1
         |where profit_top <= 10
         |order by shelve_time desc
      """.stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url, "shop_newputaway_goods", StarvConfig.properties)
    //商品销量排行
    spark.sql(
      s"""
         |select
         |shop_id,
         |sku_id,
         |sku_mapping(sku_id) as sku_name,
         |sum(num) as sale_number,
         |$dt as dt
         |from
         |dwd.dw_orders_merge_detail
         |group by shop_id,sku_id
      """.stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url, "shop_goods_sale_top", StarvConfig.properties)




    //解析hdfs_page -- 需埋点
    //    spark.sql(
    //      """
    //        |select
    //        |url,
    //        |refer,
    //        |finger_print,
    //        |user_cookies,
    //        |time_out,
    //        |from
    //        |bjubigdata_hdfs_db.ods_start_log
    //        |where url is not null  and to_date(time_in) = '$dt'
    //        |""".stripMargin).createOrReplaceTempView("hdfs_page")
    /**
     * 每个商品 访客数 -- 需埋点
     */
    //    spark.sql(
    //      s"""
    //         |witch t1 as(
    //         |SELECT
    //         |item_id,
    //         |sku_id,
    //         |finger_print
    //         |from  hdfs_page
    //         |GROUP BY item_id,sku_id,finger_print
    //         |)
    //         |select
    //         |item_id,
    //         |sku_id,
    //         |count(1) shop_user_uv
    //         |from t1
    //         |group by item_id,sku_id
    //         |""".stripMargin).createOrReplaceTempView("shop_user_visit")


    /**
     * 访问-支付转化率：
     * 支付客户数/访客数。
     * --需埋点
     */
    //    spark.sql(
    //      """
    //        |witch
    //        |succeed_user as(
    //        |select
    //        |shop_id,
    //        |sku_id,
    //        |round(sum(payment_total_money),2) as sale_succeed_money,
    //        |count(distinct buyer_id) as sale_user_count
    //        |from
    //        |dwd.dw_orders_merge_detail
    //        |where paid = 2
    //        |group by shop_id,sku_id
    //        |)
    //        |select
    //        |a.shop_id,
    //        |a.sale_succeed_money,
    //        |case when b.shop_user_uv is null
    //        |then 0 else
    //        |a.sale_user_count/b.shop_user_uv
    //        |end as sku_rate
    //        |from
    //        |succeed_user a
    //        |left join
    //        |shop_user_visit b
    //        |on a.shop_id = b.item_id and a.sku_id = b.sku_id
    //        |""".stripMargin)

    /**
     * 1、加购商品件数( 商品分析-商品明细)：
     * 被客户加入购物车的商品的件数，没有去掉加购后减少的件数。
     */
    /**
     * 加购人数( 商品分析-商品明细)：
     * 即加购的客户的数量。当筛选时，暂时不具备合计的加购客户数。
     */

    /**
     * 下单客户数( 商品分析-商品明细)：
     * 下单商品的客户数，下单即算，包含下单未支付订单，不剔除取消订单
     */
    //    spark.sql(
    //      s"""
    //         |with t1 as(
    //         |select
    //         |shop_id,
    //         |sku_id,
    //         |buyer_id
    //         |from
    //         |dwd.dw_orders_merge_detail
    //         |group by shop_id,sku_id,buyer_id
    //         |)
    //         |select
    //         |shop_id,
    //         |sku_id,
    //         |count(distinct order_id) as sale_user_count,
    //         |$dt as dt
    //         |from t1
    //         |group by shop_id,sku_id
    //         |""".stripMargin)
    //      .write
    //      .mode(SaveMode.Append)
    //      .jdbc(StarvConfig.url,"order_sale_user",StarvConfig.properties)


    /**
     * 支付件数
     * 支付人数
     * 每个商品得支付金额
     * --------------------
     * 访问-支付转化率：--需埋点
     * 支付客户数/访客数。
     * 下单客户数( 商品分析-商品明细)：
     * 下单商品的客户数，下单即算，包含下单未支付订单，不剔除取消订单
     */
    spark.sql(
      s"""
         |select
         |shop_id,
         |sku_id,
         |'all' as source_type,
         |sku_mapping(sku_id) sku_name,
         |sum(case when paid = 2 then num end) as paid_number, --支付件数
         |count(distinct case when paid = 2 then buyer_id end) as sale_user_number, -- 成交的下单用户数
         |round(sum(case when paid = 2 then payment_total_money end),2) as sale_succeed_money, --支付金额
         |count(distinct buyer_id) as all_sale_user_count, -- 总下单用户数
         |0.00 as sku_rate,
         |$dt as dt
         |from
         |dwd.dw_orders_merge_detail
         |group by shop_id,sku_id
         |""".stripMargin).union(
      spark.sql(
        s"""
           |select
           |shop_id,
           |sku_id,
           |order_source as source_type,
           |sku_mapping(sku_id) sku_name,
           |sum(case when paid = 2 then num end) as paid_number, --支付件数
           |count(distinct case when paid = 2 then buyer_id end) as sale_user_number, -- 成交的下单用户数
           |round(sum(case when paid = 2 then payment_total_money end),2) as sale_succeed_money, --支付金额
           |count(distinct buyer_id) as all_sale_user_count, -- 总下单用户数
           |0.00 as sku_rate,
           |$dt as dt
           |from
           |dwd.dw_orders_merge_detail
           |group by shop_id,sku_id,order_source
           |""".stripMargin)
    )
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url, "shop_goods_pay_info", StarvConfig.properties)

    //
    //
    //


    /**
     * 下单-转化率： -- 需埋点
     * 下单客户数/访客数
     */
    //    spark.sql(
    //      """
    //        |witch
    //        |succeed_user as(
    //        |select
    //        |shop_id,
    //        |sku_id,
    //        |count(distinct buyer_id) as sale_user_count
    //        |from
    //        |dwd.dw_orders_merge_detail
    //        |group by shop_id,sku_id
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
    //        |on a.shop_id = b.item_id and a.sku_id = b.sku_id
    //        |""".stripMargin)

    /**
     * 浏览量( 商品分析-商品明细)： -- 需埋点
     * 商品的浏览量。合计的浏览量等于每个商品的浏览量之和。
     */
    //    spark.sql(
    //      s"""
    //         |SELECT
    //         |item_id,
    //         |sku_id,
    //         |count(1) shop_pv
    //         |from  hdfs_page
    //         |GROUP BY item_id,sku_id
    //         |""".stripMargin)


    /**
     * UV价值( 商品分析-商品明细)： -- 需埋点
     * 商品的成交金额/访客数
     */
    //    spark.sql(
    //      """
    //        |witch
    //        |succeed_money as(
    //        |select
    //        |shop_id,
    //        |sku_id,
    //        |round(sum(payment_total_money),2) as sale_money_count
    //        |from
    //        |dwd.dw_orders_merge_detail
    //        |where paid = 2 and refund = 0
    //        |group by shop_id,sku_id
    //        |),
    //        |select
    //        |a.shop_id,
    //        |case when b.shop_user_uv is null
    //        |then 0 else
    //        |a.sale_money_count/b.shop_user_uv
    //        |end as uv_rate
    //        |from
    //        |succeed_money a
    //        |left join
    //        |shop_user_visit b
    //        |on a.shop_id = b.item_id and a.sku_id = b.sku_id
    //        |""".stripMargin)

    /**
     * 详情页跳出率( 商品分析-商品明细)： -- 需埋点
     * 从商品详情页直接跳出店铺的访客，
     * 占商品访客的比例。
     * -------------------
     * 需要知道详情页url
     */


  }
}
