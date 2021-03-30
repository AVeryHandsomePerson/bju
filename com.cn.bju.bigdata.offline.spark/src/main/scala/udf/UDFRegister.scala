package udf

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.UserDefinedFunction
import utils.{BroadcastUtils, TypeUtils}

/**
  * @Auther: ljh
  * @Date: 2021/3/29 22:55
  * @Description:
  */
object UDFRegister {

  def skuMapping(spark:SparkSession,dt:String): Unit ={
    val getSkuName = BroadcastUtils.getItemNameMap(spark, dt)
    spark.udf.register("sku_mapping", func = (skuId: Long) => {
      getSkuName.value.getOrElse(skuId,"")
    })
  }
  //映射 industry 名称
  def industryMapping(spark:SparkSession,dt:String): Unit ={
    val getSkuName = BroadcastUtils.getItemNameMap(spark, dt)
    spark.udf.register("industry_mapping", func = (skuId: Long) => {
      getSkuName.value.getOrElse(skuId,"")
    })
  }

  def shopMapping(spark:SparkSession,dt:String): Unit ={
    val getSkuName = BroadcastUtils.getItemNameMap(spark, dt)
    spark.udf.register("shop_mapping", func = (skuId: Long) => {
      getSkuName.value.getOrElse(skuId,"")
    })
  }

  def shopTypeMapping(spark:SparkSession,dt:String): Unit ={
    val getSkuName = BroadcastUtils.getShopTypeMap(spark, dt)
    spark.udf.register("shop_type_mapping", func = (skuId: Long) => {
      getSkuName.value.getOrElse(skuId,"")
    })
  }

  def ordersStatusMapping(spark:SparkSession): Unit ={
    spark.udf.register("orders_status_mapping", func = (skuId: String) => {
      TypeUtils.map.getOrElse(skuId,"")
    })
  }
}
