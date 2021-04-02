package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ShopNewPutAwayBean {
    Integer shopId;
    String shelveTime;
    Integer itemId;
    String itemName;
    String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShelveTime() {
        return shelveTime;
    }

    public void setShelveTime(String shelveTime) {
        this.shelveTime = shelveTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ShopNewPutAwayBean{" +
                "shopId=" + shopId +
                ", shelveTime='" + shelveTime + '\'' +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", dt=" + dt +
                '}';
    }
}
