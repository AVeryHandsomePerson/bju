package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ShopClientSaleTopBean {
    private Integer shopId;
    private String orderType;
    private String userName;
    private Integer saleSucceedMoney;
    private Integer saleSucceedProfit;
    private String dt;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(Integer saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public Integer getSaleSucceedProfit() {
        return saleSucceedProfit;
    }

    public void setSaleSucceedProfit(Integer saleSucceedProfit) {
        this.saleSucceedProfit = saleSucceedProfit;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ClientSaleTopBean{" +
                "shopId=" + shopId +
                ", orderType='" + orderType + '\'' +
                ", userName='" + userName + '\'' +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", saleSucceedProfit=" + saleSucceedProfit +
                ", dt='" + dt + '\'' +
                '}';
    }
}
