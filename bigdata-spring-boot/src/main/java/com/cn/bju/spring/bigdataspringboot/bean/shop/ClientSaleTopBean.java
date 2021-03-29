package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ClientSaleTopBean {
    private Integer shopId;
    private String order_type;
    private String user_name;
    private Integer sale_succeed_money;
    private Integer sale_succeed_profit;
    private String dt;

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getSale_succeed_money() {
        return sale_succeed_money;
    }

    public void setSale_succeed_money(Integer sale_succeed_money) {
        this.sale_succeed_money = sale_succeed_money;
    }

    public Integer getSale_succeed_profit() {
        return sale_succeed_profit;
    }

    public void setSale_succeed_profit(Integer sale_succeed_profit) {
        this.sale_succeed_profit = sale_succeed_profit;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ClientSaleTopBean{" +
                "shopId=" + shopId +
                ", order_type='" + order_type + '\'' +
                ", user_name='" + user_name + '\'' +
                ", sale_succeed_money=" + sale_succeed_money +
                ", sale_succeed_profit=" + sale_succeed_profit +
                ", dt='" + dt + '\'' +
                '}';
    }
}
