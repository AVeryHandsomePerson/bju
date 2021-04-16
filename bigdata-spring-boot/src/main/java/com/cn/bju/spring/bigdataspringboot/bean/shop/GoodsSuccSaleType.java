package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 */
public class GoodsSuccSaleType {
    private  Integer shopId;
    private  String orderType;
    private  Double saleSucceedMoney;
    private  Double saleSucceedNumber;
    private  String dt;

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

    public Double getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Double saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Double getSaleSucceedNumber() {
        return saleSucceedNumber;
    }

    public void setSaleSucceedNumber(Double saleSucceedNumber) {
        this.saleSucceedNumber = saleSucceedNumber;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "SuccessfulTransactionBean{" +
                "shopId=" + shopId +
                ", orderType='" + orderType + '\'' +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", saleSucceedNumber=" + saleSucceedNumber +
                ", dt='" + dt + '\'' +
                '}';
    }
}
