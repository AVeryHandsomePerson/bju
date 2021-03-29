package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:22
 */
public class OrderSaleUserBean {
    private Integer shopId;
    private Integer skuId;
    private Integer saleUserCount;
    private String dt;


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSaleUserCount() {
        return saleUserCount;
    }

    public void setSaleUserCount(Integer saleUserCount) {
        this.saleUserCount = saleUserCount;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "OrderSaleUserBean{" +
                "shopId=" + shopId +
                ", skuId=" + skuId +
                ", saleUserCount=" + saleUserCount +
                ", dt='" + dt + '\'' +
                '}';
    }
}
