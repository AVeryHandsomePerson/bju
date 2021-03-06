package com.cn.bju.realtime.etl.bean

import com.cn.bju.common.bean.CanalRowData
import com.cn.bju.realtime.etl.util.CommonUtils
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateUtils
import org.apache.flink.table.shaded.org.joda.time.DateTime

import java.math.BigDecimal
import scala.beans.BeanProperty

/**
 * @author ljh
 * @version 1.0
 */
case class OrderDBEntity(@BeanProperty orderId: Long, // '订单编号',
                         @BeanProperty parentOrderId: Long, // '父订单编号',
                         @BeanProperty orderType: Int, // '订单类型* 1 普通订单* 2 预售订单* 3 线下订单* 4 积分订单   * 5 无价格订单* 6 B端采购订单 (手工单-向虚拟店铺购买)* 7 B端销售订单 (手工单-向虚拟门店销售)* 8 B端采购订单 (正常单-向真实店铺购买)* 9 门店手工创建销售订单（暂时作废）* 10 客户预下单(定制)',
                         @BeanProperty status: Int, // '订单状态 0:系统处理中、1:待付款、2:待配送、3:待收货、4:待评价(已无效由其他字段控制)、5:已完成、6:已取消、7:已关闭、8:已删除、9:订单过期、10:待付尾款、11:未付尾款、12:锁单（冻结）、13:待付定金、14:待审核、15:已审核 、16:已驳回、17:待仓库发货',
                         @BeanProperty buyerStatus: Int, // '买家状态',
                         @BeanProperty sellerType: Int, // '销售类型 1普通 2定制',
                         @BeanProperty orderPlatform: String, // '所属平台',
                         @BeanProperty orderSource: String, // '订单的来源（1）PC商城（2）H5商城',
                         @BeanProperty totalMoney: Double, // '订单总金额',
                         @BeanProperty freightMoney: Double, // '订单总运费',
                         @BeanProperty discountMoney: Double, // '订单优惠总金额',
                         @BeanProperty cutMoney: Double, // '分切总金额',
                         @BeanProperty otherFee: Double, // '其他费用',
                         @BeanProperty roundDown: Double, // '抹零金额',
                         @BeanProperty actuallyPaymentMoney: Double, // '应支付的的总金额',
                         @BeanProperty buyerId: Long, // '买家id',
                         @BeanProperty buyerName: String, // '买家账号',
                         @BeanProperty buyerShopId: Long, // '买家店铺ID',
                         @BeanProperty buyerShopName: String, // '买家店铺名称',
                         @BeanProperty sellerId: Long, // '卖家id',
                         @BeanProperty sellerName: String, // '卖家账号',
                         @BeanProperty shopId: Long, // '店铺ID',
                         @BeanProperty shopName: String, // '店铺名称',
                         @BeanProperty option: String, // '订单标记',
                         @BeanProperty paid: Int, // '支付状态 1：未支付 2：已支付',
                         @BeanProperty paymentSource: String, // '支付渠道',
                         @BeanProperty paymentTime: String, // '付款时间',
                         @BeanProperty refund: Int, // '是否售后  0未退款退货  1已退款退货',
                         @BeanProperty exchange: Int, // '是否换货  0未换货  1换货',
                         @BeanProperty invoice: Int, // '是否需要发票 —— 0不需要  1普通发票  2 增值税发票',
                         @BeanProperty buyerMemo: String, // '买家备注',
                         @BeanProperty sellerMemo: String, // '卖家备注',
                         @BeanProperty isChangePrice: Int, // '是否改价  —— 0未改价 1已改价',
                         @BeanProperty changePriceUser: Long, // '改价人',
                         @BeanProperty changePriceTime: String, // '改价时间',
                         @BeanProperty settleFlag: Int, // '结算标识',
                         @BeanProperty evaluation: String, // '评价状态(1:无,2:是)',
                         @BeanProperty createTime: String, // '创建时间',
                         @BeanProperty modifyTime: String, // '修改时间',
                         @BeanProperty createUser: Long, // '创建者',
                         @BeanProperty modifyUser: Long, // '修改者',
                         @BeanProperty deposit: Double, // '预售定金',
                         @BeanProperty retainage: Double, // '预售尾款',
                         @BeanProperty retainageOrderId: Long, // '尾款支付订单编号',
                         @BeanProperty presellId: Long, // '预售Id',
                         @BeanProperty presellPayType: Int, // '预售付款类型：0：仅全款，1：仅定金',
                         @BeanProperty orderCredit: Int, // '订单使用积分',
                         @BeanProperty yn: Int, // '是否有效： 0无效  1有效（注：该字段做彻底删除用）',
                         @BeanProperty manageUserId: Long, // '经销商业务员用户ID(卖方业务员ID)',
                         @BeanProperty manageUsername: String, // '经销商业务员用户名(卖方业务员名称)',
                         @BeanProperty buyerManageUserId: Long, // '买方业务员ID',
                         @BeanProperty buyerManageUsername: String, // '买方业务员名称',
                         @BeanProperty purchaseDate: String, // '采购日期',
                         @BeanProperty warehouseCode: Long, // '仓库编码(买方入库仓库)',
                         @BeanProperty warehouseName: String, // '仓库名称(买方入库仓库)',
                         @BeanProperty reason: String, // '驳回原因',
                         @BeanProperty auditTime: String, // '审核时间',
                         @BeanProperty auditUserId: Int, // '审核人ID',
                         @BeanProperty auditUsername: String, // '审核人用户名',
                         @BeanProperty remark: String, // '备注',
                         @BeanProperty sellerOrgCode: String, // '归属卖家组织机构编码',
                         @BeanProperty sellerOrgParentCode: String, // '归属卖家上级组织机构编码',
                         @BeanProperty buyerOrgCode: String, // '归属买家组织机构编码',
                         @BeanProperty buyerOrgParentCode: String, // '归属买家上级组织机构编码',
                         @BeanProperty deliveryType: Int, // '配送方式 1：自提 2：汽运',
                         @BeanProperty printPrice: Int, // '是否打印价格： 1，否；2，是',
                         @BeanProperty consignment: Int, // '发货完成标识：1，否；2，是',
                         @BeanProperty storeComplete: Int, // '入库完成标识：1，否；2，是',
                         @BeanProperty balanceAmount: Double, // '余额支付金额',
                         @BeanProperty balanceFlag: Int, // '余额支付标识（0，否；1，是）',
                         @BeanProperty issueFlag: Int, // '订单下发标识 0：未下发 1：已下发 2：下发失败 3:无仓发货（目前只针对c端订单）',
                         @BeanProperty selfPickFlag: Int, // '1:自提订单 0:非自提订单',
                         @BeanProperty expectReceiveTime: String, // '期望收货时间',
                         @BeanProperty deliveryRemark: String, // '发货备注'
                         @BeanProperty subMchId: String, // '子商户号'
                         @BeanProperty eventType: String, //操作类型
                         @BeanProperty hour: String,  //小时
                         @BeanProperty day: String    //天
                        )

/**
 * 创建订单的伴生对象
 */
object OrderDBEntity {
  def apply(rowData: CanalRowData,eventType :String): OrderDBEntity = {
    val dataTime = new DateTime(DateUtils.parseDate(rowData.getColumns.get("create_time"), "yyyy-MM-dd HH:mm:ss"))
    //解析rowData对象为Order的样例类对象
    OrderDBEntity(
      CommonUtils.isNotNull(rowData.getColumns.get("order_id")),
      CommonUtils.isNotNull(rowData.getColumns.get("parent_order_id")),
      CommonUtils.isNotNull(rowData.getColumns.get("order_type")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("status")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("buyer_status")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("seller_type")).toInt,
      rowData.getColumns.get("order_platform"),
      rowData.getColumns.get("order_source"),
      rowData.getColumns.get("total_money").toDouble,
      rowData.getColumns.get("freight_money").toDouble,
      rowData.getColumns.get("discount_money").toDouble,
      rowData.getColumns.get("cut_money").toDouble,
      rowData.getColumns.get("other_fee").toDouble,
      rowData.getColumns.get("round_down").toDouble,
      rowData.getColumns.get("actually_payment_money").toDouble,
      CommonUtils.isNotNull(rowData.getColumns.get("buyer_id")),
      rowData.getColumns.get("buyer_name"),
      CommonUtils.isNotNull(rowData.getColumns.get("buyer_shop_id")),
      rowData.getColumns.get("buyer_shop_name"),
      CommonUtils.isNotNull(rowData.getColumns.get("seller_id")),
      rowData.getColumns.get("seller_name"),
      CommonUtils.isNotNull(rowData.getColumns.get("shop_id")),
      rowData.getColumns.get("shop_name"),
      rowData.getColumns.get("option"),
      CommonUtils.isNotNull(rowData.getColumns.get("paid")).toInt,
      rowData.getColumns.get("payment_source"),
      rowData.getColumns.get("payment_time"),
      CommonUtils.isNotNull(rowData.getColumns.get("refund")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("exchange")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("invoice")).toInt,
      rowData.getColumns.get("buyer_memo"),
      rowData.getColumns.get("seller_memo"),
      CommonUtils.isNotNull(rowData.getColumns.get("is_change_price")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("change_price_user")),
      rowData.getColumns.get("change_price_time"),
      CommonUtils.isNotNull(rowData.getColumns.get("settle_flag")).toInt,
      rowData.getColumns.get("evaluation"),
      rowData.getColumns.get("create_time"),
      rowData.getColumns.get("modify_time"),
      CommonUtils.isNotNull(rowData.getColumns.get("create_user")),
      CommonUtils.isNotNull(rowData.getColumns.get("modify_user")),
      rowData.getColumns.get("deposit").toDouble,
      rowData.getColumns.get("retainage").toDouble,
      CommonUtils.isNotNull(rowData.getColumns.get("retainage_order_id")),
      CommonUtils.isNotNull(rowData.getColumns.get("presell_id")),
      CommonUtils.isNotNull(rowData.getColumns.get("presell_pay_type")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("order_credit")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("yn")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("manage_user_id")),
      rowData.getColumns.get("manage_username"),
      CommonUtils.isNotNull(rowData.getColumns.get("buyer_manage_user_id")),
      rowData.getColumns.get("buyer_manage_username"),
      rowData.getColumns.get("purchase_date"),
      CommonUtils.isNotNull(rowData.getColumns.get("warehouse_code")),
      rowData.getColumns.get("warehouse_name"),
      rowData.getColumns.get("reason"),
      rowData.getColumns.get("audit_time"),
      CommonUtils.isNotNull(rowData.getColumns.get("audit_user_id")).toInt,
      rowData.getColumns.get("audit_username"),
      rowData.getColumns.get("remark"),
      rowData.getColumns.get("seller_org_code"),
      rowData.getColumns.get("seller_org_parent_code"),
      rowData.getColumns.get("buyer_org_code"),
      rowData.getColumns.get("buyer_org_parent_code"),
      CommonUtils.isNotNull(rowData.getColumns.get("delivery_type")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("print_price")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("consignment")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("store_complete")).toInt,
      rowData.getColumns.get("balance_amount").toDouble,
      CommonUtils.isNotNull(rowData.getColumns.get("balance_flag")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("issue_flag")).toInt,
      CommonUtils.isNotNull(rowData.getColumns.get("self_pick_flag")).toInt,
      rowData.getColumns.get("expect_receive_time"),
      rowData.getColumns.get("delivery_remark"),
      rowData.getColumns.get("sub_mchId"),
      eventType,
      dataTime.toString("HH"),
      dataTime.toString("yyyy-MM-dd")
    )
  }
}
