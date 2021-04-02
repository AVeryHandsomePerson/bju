package com.cn.bju.spring.bigdataspringboot.bean.shop;

import java.util.Date;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:12
 */
public class ShopPutawayGoodsBean {

    private Integer shopId;
    private Integer itemNumber;
    private String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "GoodsShelvesBean{" +
                "shopId=" + shopId +
                ", itemNumber=" + itemNumber +
                ", dt='" + dt + '\'' +
                '}';
    }
}
