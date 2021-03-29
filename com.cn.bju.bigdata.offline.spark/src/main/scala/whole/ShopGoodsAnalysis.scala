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
    val getSkuName = BroadcastUtils.getItemNameMap(spark, dt)
    spark.udf.register("sku_mapping", func = (skuId: Long) => {
      getSkuName.value.getOrElse(skuId,"")
    })
    spark.sql(
      s"""
         |SELECT
         |sku_id,
         |sku_mapping(sku_id),
         |count(distinct order_id) as order_number, --下单笔数
         |sum(payment_total_money) as order_money_number, --下单金额
         |count(distinct case when paid = 2  then order_id end) as payment_number, --付款笔数
         |sum(case when paid = 2  then payment_total_money end) as payment_money, --付款金额
         |round(count(distinct case when paid = 2 then buyer_id end) / count(distinct order_id),2) as payment_ratio, --支付率
         |$dt as dt
         |from
         |order
         |group by
         |sku_id
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
        |""".stripMargin)  .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)
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
         |""".stripMargin)  .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)
    //统计总订单数
    val paymentUserNumber = spark.sql("select * from dwd.dw_refund_orders where paid = 2 ").count()
    /**
     * 渠道比例
     */
    spark.sql(
      s"""
         |select
         |order_source,
         |round(count(order_id) / $paymentUserNumber,2) as payment_place_ratio,
         |$dt
         |from
         |dwd.dw_shop_order
         |where paid = 2
         |group by
         |order_source
         |""".stripMargin).write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)
    //映射 industry 名称
    val value = BroadcastUtils.getIndustryNameMap(spark, dt)
    spark.udf.register("industry_mapping", func = (industry: String) => {
      value.value.getOrElse(industry,"")
    })
    /**
     * 全平台下单行业分布比例
     */
    spark.sql(
      s"""
         |select
         |industry,
         |industry_mapping(industry) as industry_name,
         |count(order_id) as payment_order_number,
         |$dt
         |from
         |dwd.dw_shop_order
         |where paid = 2
         |group by
         |industry
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)

    /**
     * 全平台行业分布比例
     */
    spark.sql(
      s"""
         |select
         |industry,
         |industry_mapping(industry) as industry_name,
         |count(order_id) as payment_order_number,
         |$dt
         |from
         |dwd.dw_shop_order
         |group by
         |industry
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)


    /***
     * 商家营收分析
     * 总营业额：指商家的总营业额；
     * 待结算金额：指商家等待结算的金额（有些平台会将资金控制在平台内，等待结算后才到账）； -- 后续开发
     * 待退款金额：指商家店铺下待退款的金额；
     * 已退款金额：指商家店铺下已成功退款的金额；
     * 已到账金额：指商家已到账金额(一般用于提现，属于商家真实收入）； -- 后续添加
     * 退款率：指商家收到退款的订单笔数与同期成功交易（付款）的订单笔数的比率。
     */
    //订单成功总金额
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
        |""".stripMargin).createOrReplaceTempView("all_money")
    //店铺下支付成功的人数
    spark.sql(
      """
        |select
        |shop_id,
        |count(1) as count_shop_number
        |from dwd.dw_refund_orders
        |where paid = 2
        |group by shop_id
        |""".stripMargin).createOrReplaceTempView("shop_number")
    spark.sql(
      s"""
        |with t1 as (select
        |shop_id,
        |sum(case when refund_status != 5 and refund_status != 6 then refund_money end) as refund_moneys,
        |sum(case when refund_status = 6 then refund_money end) as refund_succeed_moneys,
        |count(case when refund_status = 6 then 1 end) as refund_succeed_number
        |from
        |dwd.dw_refund_orders
        |group by shop_id
        |)
        |select
        |a.shop_id,
        |b.all_payment_money,
        |a.refund_succeed_moneys,
        |a.refund_succeed_number,
        |case when refund_succeed_number/c.count_shop_number is null
        |then 0 else round(refund_succeed_number/c.count_shop_number,2) end as refund_ratio
        |from
        |t1 a
        |left join
        |all_money b
        |on a.shop_id = b.shop_id
        |left join
        |shop_number c
        |on a.shop_id = c.shop_id
        |""".stripMargin).write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)
    /**
     * 商铺级别下单行业分布比例
     */
    spark.sql(
      s"""
         |select
         |shop_id,
         |industry,
         |industry_mapping(industry),
         |count(order_id) as payment_order_number,
         |$dt
         |from
         |dwd.dw_shop_order
         |where paid = 2
         |group by
         |shop_id,industry
         |""".stripMargin)
      .write
      .mode(SaveMode.Append)
      .jdbc(StarvConfig.url,"goods_sale",StarvConfig.properties)


    val getShopName = BroadcastUtils.getShopNameMap(spark, dt)
    spark.udf.register("shop_mapping", func = (skuId: Long) => {
      getShopName.value.getOrElse(skuId,"")
    })

    spark.sql(
      """
        |select
        |shop_id,
        |shop_mapping(shop_id) shop_name,
        |all_payment_money
        |from
        |all_money
        |order by all_payment_money desc
        |""".stripMargin).show(10)



    //商家数量
    spark.sql(
      """
        |select
        |count(1) as shop_number,
        |count(case when status = 5 then 1 end) as business_number,
        |count(case when status = 4 then 1 end) as no_business_number
        |from ods.shop_info
        |""".stripMargin)
   //商家类型
    spark.sql(
      """
        |select
        |shop_type,
        |count(1) as shop_number
        |from ods.shop_info
        |group by shop_type
        |""".stripMargin)
    //








  }
}
