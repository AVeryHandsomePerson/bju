package utils

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession

/**
 * @author ljh
 * @date 2021/3/25 17:04
 * @version 1.0
 */
object BroadcastUtils {
  def getUserMap(spark: SparkSession,dt:String): Broadcast[Map[Long, String]] = {
    import spark.implicits._
    val userMap = spark.sql(
      s"""
         |select
         |id,
         |name
         |from
         |ods.ods_user
         |where dt = $dt
         |""".stripMargin)
      .map(row => (row.getLong(0), row.getString(1)))
      .collect()
      .toMap
    spark.sparkContext.broadcast(userMap)
  }

  def getIndustryNameMap(spark: SparkSession,dt:String): Broadcast[Map[String, String]] = {
    import spark.implicits._
    val industryMap = spark.sql(
      s"""
         |select
         |industry,
         |industry_name
         |from
         |ods.ods_plat_industry_rel
         |where dt = $dt
         |""".stripMargin)
      .map(row => (row.getString(0), row.getString(1)))
      .collect()
      .toMap
    spark.sparkContext.broadcast(industryMap)
  }
}
