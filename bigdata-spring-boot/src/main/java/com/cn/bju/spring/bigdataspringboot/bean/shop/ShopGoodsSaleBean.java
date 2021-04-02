package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:12
 */
public class ShopGoodsSaleBean {

    private Integer shopId;
    private String shopName;
    private Integer saleNumber;
    private Integer ordersUserNumber;
    private Integer ordersNumber;
    private Integer saleGoodsNumber;
    private Integer saleSucceedNumber;
    private Integer saleSucceedMoney;
    private Integer goodsTransformRatio;
    private String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public Integer getOrdersUserNumber() {
        return ordersUserNumber;
    }

    public void setOrdersUserNumber(Integer ordersUserNumber) {
        this.ordersUserNumber = ordersUserNumber;
    }

    public Integer getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(Integer ordersNumber) {
        this.ordersNumber = ordersNumber;
    }

    public Integer getSaleGoodsNumber() {
        return saleGoodsNumber;
    }

    public void setSaleGoodsNumber(Integer saleGoodsNumber) {
        this.saleGoodsNumber = saleGoodsNumber;
    }

    public Integer getSaleSucceedNumber() {
        return saleSucceedNumber;
    }

    public void setSaleSucceedNumber(Integer saleSucceedNumber) {
        this.saleSucceedNumber = saleSucceedNumber;
    }

    public Integer getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Integer saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Integer getGoodsTransformRatio() {
        return goodsTransformRatio;
    }

    public void setGoodsTransformRatio(Integer goodsTransformRatio) {
        this.goodsTransformRatio = goodsTransformRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopGoodsSaleBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", saleNumber=" + saleNumber +
                ", ordersUserNumber=" + ordersUserNumber +
                ", ordersNumber=" + ordersNumber +
                ", saleGoodsNumber=" + saleGoodsNumber +
                ", saleSucceedNumber=" + saleSucceedNumber +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", goodsTransformRatio=" + goodsTransformRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
