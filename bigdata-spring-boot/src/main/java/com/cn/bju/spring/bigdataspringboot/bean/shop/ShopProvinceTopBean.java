package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:33
 */
public class ShopProvinceTopBean {
  private Integer shopId;
  private String shopName;
  private String orderType;
  private String provinceName;
  private Integer saleUserCount;
  private Double saleSucceedMoney;
  private Double saleRatio;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Double getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Double saleSucceedMoney) {
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
        return "GoodsProvinceTopBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", saleUserCount=" + saleUserCount +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", saleRatio=" + saleRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
