package shop

import common.StarvConfig
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.joda.time.DateTime

object DealAnlaysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DealAnlysis")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()

    val dt = args(0)
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
     * 支付订单数，即成交单量：--合并successful_transaction
     * 统计时间内(按天、周、月统计)用户付款的总订单量，
     * 包括先款订单量(在线支付、公司转账、邮局汇款等)和货到付款订单量。
     */

    /**
     * 访客数：-- 需要埋点
     * 店铺各页面的访问人数。
     * 00:00-24:00内，同一访客多次访问只被计算一次。
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
//         |""".stripMargin)
    /**
     * 浏览量：-- 需埋点
     * 店铺各页面被用户访问的次数。
     * 用户多次打开或刷新同一个页面，浏览量累加。
     */
//    spark.sql(
//      s"""
//         |SELECT
//         |item_id,
//         |count(1) as shop_uv
//         |from  hdfs_page
//         |GROUP BY item_id
//         |""".stripMargin)
    /**
     *     成功退款金额
     * and 成功退款笔数
     * and 退款平均处理时间----运营响应时长
     * and 退款率
     * 退款数量/成交数量
     */
    spark.sql(
      s"""
         |with t1 as(
         |select
         |shop_id,
         |order_type,
         |avg(unix_timestamp(modify_time,'yyyy-MM-dd HH:mm:ss') - unix_timestamp(create_time,'yyyy-MM-dd HH:mm:ss'))
         |as avg_time,
         |sum(refund_money) as refund_money,
         |count(1) as refund_number
         |from
         |dwd.dw_refund_orders
         |where refund_status = 6
         |group by shop_id,order_type
         |),
         |t2 as (
         |select
         |shop_id,
         |order_type,
         |count(1) as save_user_count
         |from
         |dwd.dw_refund_orders
         |where paid = 2
         |group by shop_id,order_type
         |)
         |select
         |a.shop_id,
         |a.order_type,
         |round(a.avg_time,2) as avg_time,
         |a.refund_money,
         |a.refund_number,
         |b.save_user_count,
         |case when b.save_user_count is not null
         |then
         |round(a.refund_number/b.save_user_count,2)
         |else 0
         |end as quit_ratio, -- 计算单平台比例 ,全平台在后端从新计算
         |$dt as dt
         |from
         |t1 a
         |left join
         |t2 b
         |on
         |a.shop_id = b.shop_id and a.order_type = b.order_type
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"refund_index",StarvConfig.properties)


  }
}
