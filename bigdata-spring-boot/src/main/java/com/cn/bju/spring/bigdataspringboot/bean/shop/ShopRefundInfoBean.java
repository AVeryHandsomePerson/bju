package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:17
 */
public class ShopRefundInfoBean {
    private Long shopId;
    private String shopName;
    private String orderType;
    private Long ordersSucceedNumber;
    private Double avgTime;
    private Double refundMoney;
    private Double refundNumber;
    private Double refundRatio;
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

    public Long getOrdersSucceedNumber() {
        return ordersSucceedNumber;
    }

    public void setOrdersSucceedNumber(Long ordersSucceedNumber) {
        this.ordersSucceedNumber = ordersSucceedNumber;
    }

    public Double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Double avgTime) {
        this.avgTime = avgTime;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Double refundNumber) {
        this.refundNumber = refundNumber;
    }

    public Double getRefundRatio() {
        return refundRatio;
    }

    public void setRefundRatio(Double refundRatio) {
        this.refundRatio = refundRatio;
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
        return "ShopRefundInfoBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", ordersSucceedNumber=" + ordersSucceedNumber +
                ", avgTime=" + avgTime +
                ", refundMoney=" + refundMoney +
                ", refundNumber=" + refundNumber +
                ", refundRatio=" + refundRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
