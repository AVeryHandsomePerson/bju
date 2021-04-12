package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class ShopGoodsPuTypeBean {
    private Integer shopId;
    private String name;
    private Integer puType;
    private Integer puNum;
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

    public Integer getPuType() {
        return puType;
    }

    public void setPuType(Integer puType) {
        this.puType = puType;
    }

    public Integer getPuNum() {
        return puNum;
    }

    public void setPuNum(Integer puNum) {
        this.puNum = puNum;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopGoodsPuTypeBean{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", puType=" + puType +
                ", puNum=" + puNum +
                ", dt='" + dt + '\'' +
                '}';
    }
}
