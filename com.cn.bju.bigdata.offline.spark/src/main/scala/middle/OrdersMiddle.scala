package middle

import org.apache.spark.sql.SparkSession
import org.joda.time.DateTime

object OrdersMiddle {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Orders_Middle")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()
    val dt = new DateTime(args(0)).toString("yyyy-MM-dd")

    spark.sql(
      """
        |select
        |shop_id,
        |case when order_type = 1 then "TC" else "TB" end as order_type,
        |buyer_id,
        |order_id,
        |paid,
        |refund,
        |create_time,
        |order_source,
        |payment_time,
        |status,
        |seller_id
        |from
        |dwd.fact_orders
        |where parent_order_id != 0
        |""".stripMargin).createOrReplaceTempView("orders")

    spark.sqlContext.cacheTable("orders")

    /**
     * 获取所有有效订单数据
     * 订单和订单明细是一对多
     */
    spark.sql(
      s"""
         |with
         |t2 as (select
         |order_id,
         |payment_total_money+divided_balance as payment_total_money,
         |item_name,
         |cost_price,
         |sku_id,
         |num
         |from ods.ods_orders_detail
         |),
         |t3 as (
         |select
         |order_id,
         |province_name
         |from dwd.fact_orders_receive
         |)
         |insert overwrite table dwd.dw_orders_merge_detail
         |select
         |a.shop_id,
         |a.order_type,
         |a.buyer_id,
         |a.paid,
         |a.refund,
         |a.seller_id,
         |a.create_time,
         |a.payment_time,
         |a.status,
         |b.num,
         |b.sku_id,
         |b.item_name,
         |b.cost_price,
         |b.payment_total_money,
         |a.order_id,
         |c.province_name
         |from orders  a
         |left join
         |t2 b
         |on a.order_id = b.order_id
         |left join t3 c
         |on a.order_id = c.order_id
         |""".stripMargin)
    //生成 退货表+退货详情表  后续获取当天分区的
    spark.sql(
      """
        |with t1 as (
        |select
        |id,
        |order_id,
        |create_time,
        |refund_status,
        |modify_time
        |from dwd.fact_refund_apply
        |),
        |t2 as (
        |select
        |id,
        |refund_id,
        |refund_money
        |from dwd.fact_refund_detail
        |)
        |select
        |a.id,
        |a.order_id,
        |a.create_time,
        |a.refund_status,
        |a.modify_time,
        |b.refund_money
        |from
        |t1 a
        |left join
        |t2 b
        |on a.id = b.refund_id
        |""".stripMargin).createOrReplaceTempView("refund_tmp")
    //订单关联退货表 存在1对多
    spark.sql(
      s"""
         |with
         |t2 as (select
         |id,
         |order_id,
         |create_time,
         |refund_status,
         |modify_time,
         |refund_money
         |from refund_tmp
         |)
         |insert overwrite table dwd.dw_refund_orders
         |select
         |a.shop_id,
         |a.order_type,
         |a.buyer_id,
         |b.refund_status,
         |a.order_id,
         |a.paid,
         |b.id,
         |b.create_time,
         |b.modify_time,
         |b.refund_money
         |from orders a left join t2 b
         |on a.order_id = b.order_id
         |""".stripMargin)
   // 订单和商铺维度表
    spark.sql(
      """
        |select
        |a.order_id,
        |a.order_source,
        |a.paid,
        |c.industry,
        |c.industry_name
        |from
        |orders a
        |left join
        |(select * from ods.shop_info) b
        |on a.shop_id = b.shop_id
        |left join
        |(select * from ods.ods_plat_industry_rel) c
        |on b.plat_industry_rel_id = c.id
        |""".stripMargin).createOrReplaceTempView("dw_shop_order")




  }
}
