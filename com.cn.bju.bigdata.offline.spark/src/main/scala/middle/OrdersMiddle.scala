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
    val dt = args(0)
    spark.sql(
      s"""
        |select
        |shop_id,
        |case when order_type = 1 then "TC" else "TB" end as order_type,
        |case when (order_type = 6 or order_type = 8) then "PO" end as po_type,
        |buyer_id,
        |order_id,
        |paid,
        |refund,
        |create_time,
        |order_source,
        |payment_time,
        |status,
        |actually_payment_money,
        |seller_id
        |from
        |dwd.fact_orders
        |where parent_order_id != 0 and dt = $dt and end_zipper_time = '9999-12-31'
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
         |if(payment_total_money is null,0,payment_total_money)
         |+
         |if(divided_balance is null,0,divided_balance) as payment_total_money,
         |item_name,
         |cost_price,
         |sku_id,
         |cid,
         |num
         |from dwd.fact_orders_detail
         |where dt=$dt
         |),
         |t3 as (
         |select
         |order_id,
         |province_name
         |from dwd.fact_orders_receive
         |where dt=$dt
         |)
         |insert overwrite table dwd.dw_orders_merge_detail
         |select
         |distinct
         |a.shop_id,
         |a.order_type,
         |a.po_type,
         |a.buyer_id,
         |a.paid,
         |a.refund,
         |a.seller_id,
         |a.create_time,
         |a.payment_time,
         |a.status,
         |a.order_source,
         |b.num,
         |b.sku_id,
         |b.item_name,
         |b.cost_price,
         |b.payment_total_money,
         |a.order_id,
         |b.cid,
         |c.province_name
         |from orders  a
         |left join
         |t2 b
         |on a.order_id = b.order_id
         |left join t3 c
         |on a.order_id = c.order_id
         |""".stripMargin)



    //获取 退货表 退货明细表 后续获取当天分区的
    spark.sql(
      s"""
        |select
        |id,
        |order_id,
        |refund_status,
        |refund_reason,
        |modify_time
        |from dwd.fact_refund_apply
        |where dt = $dt and end_zipper_time = '9999-12-31'
        |""".stripMargin).createOrReplaceTempView("refund_apply")
    spark.sql(
      s"""
        |select
        |id,
        |refund_id,
        |create_time,
        |sku_id,
        |refund_num,
        |refund_price
        |from dwd.fact_refund_detail
        |where dt = $dt and end_zipper_time = '9999-12-31'
        |""".stripMargin).createOrReplaceTempView("refund_detail")
    //退货表和退货明细表合并
     spark.sql(
       """
         |select
         |a.id,
         |a.order_id,
         |a.refund_status,
         |a.refund_reason,
         |a.modify_time,
         |b.create_time,
         |b.refund_id,
         |b.sku_id,
         |b.refund_num * b.refund_price as refund_money
         |from
         |refund_apply a
         |left join
         |refund_detail b
         |on a.id = b.refund_id
         |""".stripMargin).createOrReplaceTempView("refund_tmp")
    //退货表关联订单 存在1对多
    spark.sql(
      s"""
         |insert overwrite table dwd.dw_refund_orders
         |select
         |a.id,
         |a.create_time,
         |a.modify_time,
         |a.refund_reason,
         |a.refund_money,
         |a.sku_id,
         |b.shop_id,
         |b.order_type,
         |b.buyer_id,
         |b.refund,
         |a.refund_status,
         |b.order_id,
         |b.paid,
         |b.actually_payment_money
         |from refund_tmp a
         |left join
         |orders b
         |on a.order_id = b.order_id
         |""".stripMargin)
    //生成sku 信息和item 商城表 关联出 sku 和 商品名称总表 按天分区
    spark.sql (
      s"""
        |insert overwrite table dwd.dwd_sku_name
        |select
        |a.sku_id,
        |b.item_name,
        |a.status,
        |b.cid
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
        |dwd.fact_item
        |where end_zipper_time = '9999-12-31'
        |) b
        |on a.item_id = b.item_id
        |""".stripMargin)
    //商品表维度数据拉宽
    spark.sql(
      s"""
        |insert overwrite table dwd.dim_goods_cat partition(dt=$dt)
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
