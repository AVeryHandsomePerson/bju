package com.cn.bju.spring.bigdataspringboot.bean.platform;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:27
 */
public class PaltFormGoodsInfoBean {


    private Long skuId;
    private String itemName;
    private String catName;
    private String status;
    private Long ordersSucceedNumber;
    private double saleOrderNumber;
    private double saleSucceedMoney;
    private double money;
    private String dt;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrdersSucceedNumber() {
        return ordersSucceedNumber;
    }

    public void setOrdersSucceedNumber(Long ordersSucceedNumber) {
        this.ordersSucceedNumber = ordersSucceedNumber;
    }

    public double getSaleOrderNumber() {
        return saleOrderNumber;
    }

    public void setSaleOrderNumber(double saleOrderNumber) {
        this.saleOrderNumber = saleOrderNumber;
    }

    public double getSaleSucceedMoney() {
        return saleSucceedMoney;
    }

    public void setSaleSucceedMoney(double saleSucceedMoney) {
        this.saleSucceedMoney = saleSucceedMoney;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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
        return "PaltFromGoodsInfoBean{" +
                "skuId=" + skuId +
                ", itemName='" + itemName + '\'' +
                ", catName='" + catName + '\'' +
                ", status='" + status + '\'' +
                ", ordersSucceedNumber=" + ordersSucceedNumber +
                ", saleOrderNumber=" + saleOrderNumber +
                ", saleSucceedMoney=" + saleSucceedMoney +
                ", money=" + money +
                ", dt='" + dt + '\'' +
                '}';
    }
}
