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
    //生成 退货表  后续获取当天分区的
    spark.sql(
      """
        |select
        |id,
        |order_id,
        |create_time,
        |refund_status,
        |modify_time,
        |refund_total_money as refund_money
        |from dwd.fact_refund_apply
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
    //生成sku 信息和item 商城表 关联出 sku 和 商品名称总表 按天分区
    spark.sql(
      s"""
        |insert overwrite table dwd.dwd_sku_name
        |select
        |a.sku_id,
        |b.item_name
        |from
        |(select
        |*
        |from
        |ods.ods_item_sku
        |where dt = $dt) a
        |left join
        |(
        |select
        |*
        |from
        |ods.ods_item
        |) b
        |on a.item_id = b.item_id
        |""".stripMargin)
    //商品表维度数据拉宽
    spark.sql(
      s"""
        |insert overwrite table dwd.dim_goods_cat partition(dt='20210325')
        |select
        |t3.cid as cat_3d_id,   --三级类目id
        |t3.name as cat_3d_name,  --三级类目名称
        |t2.cid as cat_2d_id,  --二级类目id
        |t2.name as cat_2d_name, --二级类目名称
        |t1.cid as cat_1t_id, --一级类目id
        |t1.name as cat_1t_name --一级类目名称
        |from
        |(select
        |*
        |from ods.ods_item_category
        |where level = 3 and  dt=$dt) t3
        |left join
        |(select
        |*
        |from ods.ods_item_category
        |where level = 2 and  dt=$dt) t2
        |on t3.parent_cid = t2.cid
        |left join
        |(select
        |*
        |from ods.ods_item_category
        |where level = 1 and  dt=$dt) t1
        |on t2.parent_cid = t1.cid
        |""".stripMargin)







  }
}
