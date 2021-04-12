package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class ShopGoodsPuInfoBean {
    private Integer shopId;
    private String name;
    private Integer puNum;
    private Double puMoney;
    private String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPuNum() {
        return puNum;
    }

    public void setPuNum(Integer puNum) {
        this.puNum = puNum;
    }

    public Double getPuMoney() {
        return puMoney;
    }

    public void setPuMoney(Double puMoney) {
        this.puMoney = puMoney;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopGoodsPuInfoBean{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", puNum=" + puNum +
                ", puMoney=" + puMoney +
                ", dt='" + dt + '\'' +
                '}';
    }
}
