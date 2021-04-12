package shop

import common.StarvConfig
import org.apache.commons.lang3.time.DateUtils
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.DateTime
import udf.UDFRegister

/**
 * @author ljh
 * @date 2021/4/7 15:58
 * @version 1.0
 */
object WarehouseAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Goods_info")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()

    val dt = args(0)
    //初始化udf
    UDFRegister.skuMapping(spark, dt)
    UDFRegister.brandThreeMapping(spark, dt)
    //-----------------------------------出入库统计
    //---------- 商品入库信息
    spark.sql(
      s"""
         |select
         |*
         |from
         |ods.ods_inbound_bill
         |where dt = $dt
         |and (date_format(create_time,'yyyyMMdd') = "20210331"
         |or date_format(modify_time,'yyyyMMdd') = "20210331" )
         |and status !=3 and status !=5
         |""".stripMargin).createOrReplaceTempView("inbound_bill")
    spark.sql(
      s"""
         |select
         |*
         |from
         |ods.ods_inbound_bill_detail
         |where dt = $dt
         |and date_format(create_time,'yyyyMMdd') = "20210331"
         |""".stripMargin).createOrReplaceTempView("inbound_bill_detail")
    spark.sql(
      s"""
         |
         |select
         |t1.shop_id,
         |t1.warehouse_code,
         |t1.warehouse_name,
         |t2.item_id,
         |t2.sku_id,
         |t2.price,
         |t2.real_inbound_num,
         |$dt as dt
         |from
         |inbound_bill t1
         |left join
         |inbound_bill_detail t2
         |on t1.id = t2.inbound_bill_id
         |""".stripMargin).createOrReplaceTempView("inbound_merge_detail")
    //----------- 商品出库信息
    spark.sql(
      s"""
         |select
         |*
         |from
         |ods.ods_outbound_bill
         |where dt = $dt
         |and (date_format(create_time,'yyyyMMdd') = "20210331"
         |or date_format(modify_time,'yyyyMMdd') = "20210331" )
         |and status !=3 and status !=5
         |""".stripMargin).createOrReplaceTempView("outbound_bill")
    spark.sql(
      s"""
         |select
         |*
         |from
         |ods.ods_outbound_bill_detail
         |where dt = $dt
         |and date_format(create_time,'yyyyMMdd') = "20210331"
         |""".stripMargin).createOrReplaceTempView("outbound_bill_detail")
    spark.sql(
      """
        |
        |select
        |t1.shop_id,
        |t1.warehouse_code,
        |t1.warehouse_name,
        |t2.sku_id,
        |t2.price,
        |t2.real_outbound_num
        |from
        |outbound_bill t1
        |left join
        |outbound_bill_detail t2
        |on t1.id = t2.outbound_bill_id
        |""".stripMargin).createOrReplaceTempView("outbound_merge_detail")
    //商品维度 以入库表为主表,算出既有入库又有出库的商品
    val skuInOut = spark.sql(
      s"""
         |with t1 as (
         |select
         |shop_id,
         |sku_id,
         |sku_mapping(sku_id) sku_name,
         |sum(real_inbound_num) as real_inbound_num
         |from
         |inbound_merge_detail
         |group by shop_id,sku_id
         |),
         |t2 as (
         |select
         |shop_id,
         |sku_id,
         |sku_mapping(sku_id) sku_name,
         |sum(real_outbound_num) as real_outbound_num
         |from
         |outbound_merge_detail
         |group by shop_id,sku_id
         |),
         |t3 as (select
         |t1.shop_id,
         |t1.sku_id,
         |t1.sku_name,
         |t1.real_inbound_num,
         |t2.real_outbound_num
         |from
         |t1 left join t2
         |on t1.shop_id = t2.shop_id
         |and t1.sku_id = t2.sku_id
         |),
         |t4 as (select -- 只有出库的
         |t2.shop_id,
         |t2.sku_id,
         |t2.sku_name,
         |t1.real_inbound_num,
         |t2.real_outbound_num
         |from
         |t2 left join t1
         |on t2.shop_id = t1.shop_id
         |and t2.sku_id = t1.sku_id
         |where t1.real_inbound_num is null
         |)
         |select
         |shop_id,
         |sku_id as business_id,
         |sku_name as business_name,
         |real_inbound_num,
         |real_outbound_num,
         |'1' as business_type, --商品
         |$dt as dt
         |from
         |t3
         |union all
         |select
         |shop_id,
         |sku_id as business_id,
         |sku_name as business_name,
         |real_inbound_num,
         |real_outbound_num,
         |'1' as business_type, --商品
         |$dt as dt
         |from t4
         |""".stripMargin)
    //仓库信息
    val warehouseInOut =spark.sql(
      s"""
         |with t1 as (
         |select
         |shop_id,
         |warehouse_code,
         |warehouse_name,
         |sum(real_inbound_num) as real_inbound_num
         |from
         |inbound_merge_detail
         |group by shop_id,warehouse_code,warehouse_name
         |),
         |t2 as (
         |select
         |shop_id,
         |warehouse_code,
         |warehouse_name,
         |sum(real_outbound_num) as real_outbound_num
         |from
         |outbound_merge_detail
         |group by shop_id,warehouse_code,warehouse_name
         |),
         |t3 as (select
         |t1.shop_id,
         |t1.warehouse_code,
         |t1.warehouse_name,
         |t1.real_inbound_num,
         |t2.real_outbound_num
         |from
         |t1 left join t2
         |on t1.shop_id = t2.shop_id
         |and t1.warehouse_code = t2.warehouse_code
         |),
         |t4 as (select -- 只有出库的
         |t2.shop_id,
         |t2.warehouse_code,
         |t2.warehouse_name,
         |t1.real_inbound_num,
         |t2.real_outbound_num
         |from
         |t2 left join t1
         |on t2.shop_id = t1.shop_id
         |and t2.warehouse_code = t1.warehouse_code
         |where t1.real_inbound_num is null
         |)
         |select
         |shop_id,
         |warehouse_code as business_id,
         |warehouse_name as business_name,
         |real_inbound_num,
         |real_outbound_num,
         |'2' as business_type, --仓库
         |$dt as dt
         |from
         |t3
         |union all
         |select
         |shop_id,
         |warehouse_code as business_id,
         |warehouse_name as business_name,
         |real_inbound_num,
         |real_outbound_num,
         |'2' as business_type, --仓库
         |$dt as dt
         |from t4
         |""".stripMargin)

    skuInOut.union(warehouseInOut)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url, "shop_warehouse_inout", StarvConfig.properties)
    //-----------------------------------库存成本
    spark.sql(
      s"""
         |with t1 as(select
         |*
         |from
         |ods.ods_warehouse_item
         |where dt = $dt
         |),
         |t2 as (
         |select
         |*
         |from
         |ods.ods_item_master
         |where dt = $dt
         |)
         |select
         |t1.shop_id,
         |t1.sku_id,
         |t1.inventory,
         |t1.warehouse_code,
         |t1.warehouse_name,
         |t1.item_id,
         |t2.brand_id
         |from
         |t1 left join t2 on
         |t1.shop_id = t2.shop_id
         |and t1.item_id = t2.id
         |""".stripMargin).createOrReplaceTempView("warehouse_item")
    spark.sql(
      s"""
         |select
         |t1.shop_id,
         |t1.sku_id,
         |t1.inventory,
         |t1.warehouse_code,
         |t1.warehouse_name,
         |t1.item_id,
         |t1.brand_id,
         |case when t3.price is null then 0 else t3.price end as price
         |from
         |warehouse_item as t1 left join
         |inbound_merge_detail t3
         |on t1.shop_id = t3.shop_id and t1.item_id = t3.item_id
         |""".stripMargin).createOrReplaceTempView("warehouse_tmp")
    //商品维度
    val skuDf = spark.sql(
      s"""
         |with t1 as(select
         |shop_id,
         |sku_id,
         |sku_mapping(sku_id) sku_name,
         |sum(inventory) as inventory
         |from
         |warehouse_item
         |group by shop_id,sku_id
         |),
         |t2 as (
         |select
         |shop_id,
         |sku_id,
         |sum(price * inventory) as number_money
         |from
         |warehouse_tmp
         |group by shop_id,sku_id
         |)
         |select
         |t1.shop_id,
         |t1.sku_id  as business_id,
         |t1.sku_name as business_name,
         |t1.inventory,
         |t2.number_money,
         |'1' as business_type, --商品
         |$dt as dt
         |from
         |t1 left join t2
         |on t1.shop_id =t2.shop_id and t1.sku_id = t2.sku_id
         |""".stripMargin)
    //仓库维度
    val warehouseDf = spark.sql(
      s"""
         |with t1 as(select
         |shop_id,
         |warehouse_code,
         |warehouse_name,
         |sum(inventory) as inventory
         |from
         |warehouse_item
         |group by shop_id,warehouse_code,warehouse_name
         |),
         |t2 as (
         |select
         |shop_id,
         |warehouse_code,
         |warehouse_name,
         |sum(price * inventory) as number_money
         |from
         |warehouse_tmp
         |group by shop_id,warehouse_code,warehouse_name
         |)
         |select
         |t1.shop_id,
         |t1.warehouse_code as business_id,
         |t1.warehouse_name  as business_name,
         |t1.inventory,
         |t2.number_money,
         |'2' as business_type, --仓库
         |$dt as dt
         |from
         |t1 left join t2
         |on t1.shop_id =t2.shop_id and t1.warehouse_code = t2.warehouse_code
         |and t1.warehouse_name = t2.warehouse_name
         |""".stripMargin)
    //品牌维度
    val brandDf = spark.sql(
      s"""
         |with t1 as(select
         |shop_id,
         |brand_id,
         |brand_three_mapping(brand_id) as brand_name,
         |sum(inventory) as inventory
         |from
         |warehouse_item
         |group by shop_id,brand_id
         |),
         |t2 as (
         |select
         |shop_id,
         |brand_id,
         |sum(price * inventory) as number_money
         |from
         |warehouse_tmp
         |group by shop_id,brand_id
         |)
         |select
         |t1.shop_id,
         |t1.brand_id  as business_id,
         |t1.brand_name as business_name,
         |t1.inventory,
         |t2.number_money,
         |'3' as business_type, --品牌
         |$dt as dt
         |from
         |t1 left join t2
         |on t1.shop_id =t2.shop_id and t1.brand_id = t2.brand_id
         |""".stripMargin)
    skuDf.union(warehouseDf).union(brandDf)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url, "shop_warehouse_info", StarvConfig.properties)


  }
}
