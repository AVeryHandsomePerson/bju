package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:24
 */
public class PayIndexInfoBean {

    private Long shopId;
    private Long skuId;
    private Double paidNumber;
    private Long saleUserCount;
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

    public Double getPaidNumber() {
        return paidNumber;
    }

    public void setPaidNumber(Double paidNumber) {
        this.paidNumber = paidNumber;
    }

    public Long getSaleUserCount() {
        return saleUserCount;
    }

    public void setSaleUserCount(Long saleUserCount) {
        this.saleUserCount = saleUserCount;
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

    @Override
    public String toString() {
        return "PayIndexInfoBean{" +
                "shopId=" + shopId +
                ", skuId=" + skuId +
                ", paidNumber=" + paidNumber +
                ", saleUserCount=" + saleUserCount +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", skuRate=" + skuRate +
                ", dt='" + dt + '\'' +
                '}';
    }
}
