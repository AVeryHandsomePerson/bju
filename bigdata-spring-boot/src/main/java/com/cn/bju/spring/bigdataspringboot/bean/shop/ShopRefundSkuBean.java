package com.cn.bju.spring.bigdataspringboot.bean.shop;

import java.math.BigDecimal;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:17
 */
public class ShopRefundSkuBean {
    private Long shopId;
    private String shopName;
    private Long skuId;
    private String orderType;
    private String skuName;
    private Long refundReasonNumber;
    private Double refundMoney;
    private Long ordersSucceedMoney;
    private Long refundNumber;
    private Double refundRatio;
    private Double refundReasonRatio;
    private String dt;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Long getRefundReasonNumber() {
        return refundReasonNumber;
    }

    public void setRefundReasonNumber(Long refundReasonNumber) {
        this.refundReasonNumber = refundReasonNumber;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Long getOrdersSucceedMoney() {
        return ordersSucceedMoney;
    }

    public void setOrdersSucceedMoney(Long ordersSucceedMoney) {
        this.ordersSucceedMoney = ordersSucceedMoney;
    }

    public Long getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Long refundNumber) {
        this.refundNumber = refundNumber;
    }

    public Double getRefundRatio() {
        return refundRatio;
    }

    public void setRefundRatio(Double refundRatio) {
        this.refundRatio = refundRatio;
    }

    public Double getRefundReasonRatio() {
        return refundReasonRatio;
    }

    public void setRefundReasonRatio(Double refundReasonRatio) {
        this.refundReasonRatio = refundReasonRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopRefundSkuBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", skuId=" + skuId +
                ", orderType='" + orderType + '\'' +
                ", skuName='" + skuName + '\'' +
                ", refundReasonNumber=" + refundReasonNumber +
                ", refundMoney=" + refundMoney +
                ", ordersSucceedMoney=" + ordersSucceedMoney +
                ", refundNumber=" + refundNumber +
                ", refundRatio=" + refundRatio +
                ", refundReasonRatio=" + refundReasonRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
