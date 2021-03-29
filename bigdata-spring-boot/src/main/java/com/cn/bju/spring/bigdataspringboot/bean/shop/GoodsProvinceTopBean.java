package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:33
 */
public class GoodsProvinceTopBean {
    private Integer shopId;
    private String provinceName;
    private  Integer saleUserCount;
    private  Integer saleSucceedMoney;
    private  Double saleRatio;
    private  String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getSaleUserCount() {
        return saleUserCount;
    }

    public void setSaleUserCount(Integer saleUserCount) {
        this.saleUserCount = saleUserCount;
    }

    public Integer getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Integer saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Double getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(Double saleRatio) {
        this.saleRatio = saleRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "GoodsPriovinceTopBean{" +
                "shopId=" + shopId +
                ", provinceName='" + provinceName + '\'' +
                ", saleUserCount=" + saleUserCount +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", saleRatio=" + saleRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
