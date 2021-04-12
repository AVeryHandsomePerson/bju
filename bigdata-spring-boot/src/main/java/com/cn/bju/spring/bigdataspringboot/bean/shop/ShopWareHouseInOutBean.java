package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class ShopWareHouseInOutBean {
    private Long shopId;
    private Long businessId;
    private String businessName;
    private Long realInboundNum;
    private Long realOutboundNum;
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

    public Long getRealInboundNum() {
        return realInboundNum;
    }

    public void setRealInboundNum(Long realInboundNum) {
        this.realInboundNum = realInboundNum;
    }

    public Long getRealOutboundNum() {
        return realOutboundNum;
    }

    public void setRealOutboundNum(Long realOutboundNum) {
        this.realOutboundNum = realOutboundNum;
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
        return "ShopWareHouseInOutBean{" +
                "shopId=" + shopId +
                ", businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", realInboundNum=" + realInboundNum +
                ", realOutboundNum=" + realOutboundNum +
                ", businessType='" + businessType + '\'' +
                ", dt='" + dt + '\'' +
                '}';
    }
}
