package com.cn.bju.spring.bigdataspringboot.bean.shop;

import java.math.BigDecimal;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class ShopWareHouseInfoBean {
    private Long shopId;
    private Long businessId;
    private String businessName;
    private Long inventory;
    private BigDecimal number_money;
    private String businessType;
    private String dt;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getNumber_money() {
        return number_money;
    }

    public void setNumber_money(BigDecimal number_money) {
        this.number_money = number_money;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopWareHouseInfoBean{" +
                "shopId=" + shopId +
                ", businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", inventory=" + inventory +
                ", number_money=" + number_money +
                ", businessType='" + businessType + '\'' +
                ", dt='" + dt + '\'' +
                '}';
    }
}
