package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:24
 */
public class ShopGoodsPayInfoBean {

    private Long shopId;
    private Long skuId;
    private String sourceType;
    private String skuName;
    private Double paidNumber;
    private Long saleUserNumber;
    private Long allSaleUserCount;
    private Double saleSucceedMoney;
    private Double skuRate;
    private String dt;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Double getPaidNumber() {
        return paidNumber;
    }

    public void setPaidNumber(Double paidNumber) {
        this.paidNumber = paidNumber;
    }

    public Long getSaleUserNumber() {
        return saleUserNumber;
    }

    public void setSaleUserNumber(Long saleUserNumber) {
        this.saleUserNumber = saleUserNumber;
    }

    public Long getAllSaleUserCount() {
        return allSaleUserCount;
    }

    public void setAllSaleUserCount(Long allSaleUserCount) {
        this.allSaleUserCount = allSaleUserCount;
    }

    public Double getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Double saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Double getSkuRate() {
        return skuRate;
    }

    public void setSkuRate(Double skuRate) {
        this.skuRate = skuRate;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "ShopGoodsPayInfoBean{" +
                "shopId=" + shopId +
                ", skuId=" + skuId +
                ", sourceType='" + sourceType + '\'' +
                ", skuName='" + skuName + '\'' +
                ", paidNumber=" + paidNumber +
                ", saleUserNumber=" + saleUserNumber +
                ", allSaleUserCount=" + allSaleUserCount +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", skuRate=" + skuRate +
                ", dt='" + dt + '\'' +
                '}';
    }
}
