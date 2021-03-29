package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ClientOnePriceBean {
    private Integer shopId;
    private Double money;
    private String dt;


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ClientOnePriceBean{" +
                "shopId=" + shopId +
                ", money=" + money +
                ", dt='" + dt + '\'' +
                '}';
    }
}
