package whole

import common.StarvConfig
import org.apache.spark.sql.{SaveMode, SparkSession}
import utils.BroadcastUtils

/**
 * @author ljh
 * @date 2021/3/29 9:36
 * @version 1.0
 */
object ShopGoodsAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DealAnlysis")
      .config("hive.exec.dynamici.partition", true)
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .enableHiveSupport()
      .getOrCreate()

    val dt = args(0)


    spark.sql(
      """
        |select
        |*
        |from
        |dwd.dw_orders_merge_detail
        |""".stripMargin).createOrReplaceTempView("order")
    spark.sqlContext.cacheTable("order")
    /**
     *下单笔数
     *下单金额
     *付款笔数
     *付款金额 = 支付金额
     *支付率
     */
    spark.sql(
      s"""
         |SELECT
         |count(distinct order_id) as order_number, --下单笔数
         |sum(payment_total_money) as order_money_number, --下单金额
         |count(distinct case when paid = 2  then order_id end) as payment_number, --付款笔数
         |sum(case when paid = 2  then payment_total_money end) as payment_money, --付款金额
         |count(distinct case when paid = 2 then buyer_id end) / count(distinct order_id) as payment_ratio, --支付率
         |$dt as dt
         |from
         |order
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)
    /**
     * 订单状态分布
     */
    spark.sql(
      s"""
        |select
        |status,
        |count(distinct order_id) as status_type_number,
        |$dt
        |from
        |order
        |group by
        |status
        |""".stripMargin)
    /**
     * 消费时间段分布
     */
    spark.sql(
      s"""
         |select
         |hour(payment_time) as payment_time,
         |count(distinct order_id) as payment_order_number,
         |$dt
         |from
         |order
         |where paid = 2
         |group by
         |hour(payment_time)
         |""".stripMargin)


    //统计总订单数
    val paymentUserNumber = spark.sql("select order_id from dw_shop_order where paid = 2").count()
    /**
     * 渠道比例
     * order_source|payment_place_ratio|20210325|
+------------+-------------------+--------+
|          PC|               0.53|20210325|
|          H5|               0.47|20210325|
     */
    spark.sql(
      s"""
         |select
         |order_source,
         |round(count(order_id) / $paymentUserNumber,2) as payment_place_ratio,
         |$dt
         |from
         |dw_shop_order
         |where paid = 2
         |group by
         |order_source
         |""".stripMargin)

    //映射 industry 名称
    val value = BroadcastUtils.getIndustryNameMap(spark, dt)
    spark.udf.register("industry_mapping", func = (industry: String) => {
      value.value.getOrElse(industry,"")
    })
    /**
     * 渠道比例
     */
    spark.sql(
      s"""
         |select
         |industry,
         |industry_mapping(industry),
         |count(order_id) as payment_order_number,
         |$dt
         |from
         |dw_shop_order
         |where paid = 2
         |group by
         |industry
         |""".stripMargin)


    /***
     * 商家营收分析
     * 总营业额：指商家的总营业额；
     * 待结算金额：指商家等待结算的金额（有些平台会将资金控制在平台内，等待结算后才到账）；
     * 待退款金额：指商家店铺下待退款的金额；
     * 已退款金额：指商家店铺下已成功退款的金额；
     * 已到账金额：指商家已到账金额(一般用于提现，属于商家真实收入）；
     * 退款率：指商家收到退款的订单笔数与同期成功交易（付款）的订单笔数的比率。
     */
    spark.sql(
      """
        |select
        |shop_id,
        |sum(payment_total_money) as all_payment_money
        |from
        |order
        |where paid = 2 and refund = 0
        |group by
        |shop_id
        |""".stripMargin)

    spark.sql(
      """
        |select
        |sum(refund_money) as refund_moneys
        |from
        |dwd.dw_refund_orders
        |where refund_status != 5 and refund_status != 6
        |group by shop_id
        |""".stripMargin)








  }
}
