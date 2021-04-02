package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ShopSaleSucceedInfoBean {
    private Integer  shopId;
    private String  orderType;
    private Integer  saleUserNumber;
    private Integer  ordersSucceedNumber;
    private Integer  saleOrderNumber;
    private Double  saleSucceedMoney;
    private Double  money;
    private String  dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getSaleUserNumber() {
        return saleUserNumber;
    }

    public void setSaleUserNumber(Integer saleUserNumber) {
        this.saleUserNumber = saleUserNumber;
    }

    public Integer getOrdersSucceedNumber() {
        return ordersSucceedNumber;
    }

    public void setOrdersSucceedNumber(Integer ordersSucceedNumber) {
        this.ordersSucceedNumber = ordersSucceedNumber;
    }

    public Integer getSaleOrderNumber() {
        return saleOrderNumber;
    }

    public void setSaleOrderNumber(Integer saleOrderNumber) {
        this.saleOrderNumber = saleOrderNumber;
    }

    public Double getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Double saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ClientOnePriceBean{" +
                "shopId=" + shopId +
                ", orderType='" + orderType + '\'' +
                ", saleUserNumber=" + saleUserNumber +
                ", ordersSucceedNumber=" + ordersSucceedNumber +
                ", saleOrderNumber=" + saleOrderNumber +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", money=" + money +
                ", dt='" + dt + '\'' +
                '}';
    }
}
