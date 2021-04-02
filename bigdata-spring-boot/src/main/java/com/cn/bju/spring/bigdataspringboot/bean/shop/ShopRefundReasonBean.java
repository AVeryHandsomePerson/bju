package com.cn.bju.spring.bigdataspringboot.bean.shop;

import java.math.BigDecimal;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:17
 */
public class ShopRefundReasonBean {
    private Long shopId;
    private String shopName;
    private String orderType;
    private Long refundReasonNumber;
    private BigDecimal refundMoney;
    private Long refundNumber;
    private BigDecimal refundNumberRatio;
    private BigDecimal refundMoneyRatio;
    private String dt;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getRefundReasonNumber() {
        return refundReasonNumber;
    }

    public void setRefundReasonNumber(Long refundReasonNumber) {
        this.refundReasonNumber = refundReasonNumber;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Long getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Long refundNumber) {
        this.refundNumber = refundNumber;
    }

    public BigDecimal getRefundNumberRatio() {
        return refundNumberRatio;
    }

    public void setRefundNumberRatio(BigDecimal refundNumberRatio) {
        this.refundNumberRatio = refundNumberRatio;
    }

    public BigDecimal getRefundMoneyRatio() {
        return refundMoneyRatio;
    }

    public void setRefundMoneyRatio(BigDecimal refundMoneyRatio) {
        this.refundMoneyRatio = refundMoneyRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "ShopRefundReasonBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", refundReasonNumber=" + refundReasonNumber +
                ", refundMoney=" + refundMoney +
                ", refundNumber=" + refundNumber +
                ", refundNumberRatio=" + refundNumberRatio +
                ", refundMoneyRatio=" + refundMoneyRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
