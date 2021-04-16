package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 */
public class GoodsSuccSale {
    private  Integer shopId;
    private  Double saleSucceedMoney;
    private  Double saleSucceedNumber;
    private  String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", saleSucceedNumber=" + saleSucceedNumber +
                ", dt='" + dt + '\'' +
                '}';
    }
}
