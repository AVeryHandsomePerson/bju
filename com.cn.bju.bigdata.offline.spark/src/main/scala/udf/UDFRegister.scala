package udf

import org.apache.spark.sql.SparkSession
import utils.{BroadcastUtils, TypeUtils}

/**
  * @Auther: ljh
  * @Date: 2021/3/29 22:55
  * @Description:
  */
object UDFRegister{

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
    val getSkuName = BroadcastUtils.getShopNameMap(spark, dt)
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
    val map = TypeUtils.map
    spark.udf.register("orders_status_mapping", func = (skuId: Long) => {
      map.getOrElse(skuId.toString,"")
    })
  }

  def clientMapping(spark:SparkSession,dt:String): Unit ={
    val userMap = BroadcastUtils.getUserMap(spark, dt)
    spark.udf.register("user_mapping", func = (userId: Long) => {
      userMap.value.getOrElse(userId,"")
    })
  }
}
