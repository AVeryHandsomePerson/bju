package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:31
 */
public class ShopGoodsProfitTopBean {
    private Integer shopId;
    private String itemName;
    private  Integer saleUserCount;
    private  Integer saleSucceedProfit;
    private  Double saleProfitRatio;
    private  String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSaleUserCount() {
        return saleUserCount;
    }

    public void setSaleUserCount(Integer saleUserCount) {
        this.saleUserCount = saleUserCount;
    }

    public Integer getSaleSucceedProfit() {
        return saleSucceedProfit;
    }

    public void setSaleSucceedProfit(Integer saleSucceedProfit) {
        this.saleSucceedProfit = saleSucceedProfit;
    }

    public Double getSaleProfitRatio() {
        return saleProfitRatio;
    }

    public void setSaleProfitRatio(Double saleProfitRatio) {
        this.saleProfitRatio = saleProfitRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "GoodsProfitTopBean{" +
                "shopId=" + shopId +
                ", itemName='" + itemName + '\'' +
                ", saleUserCount=" + saleUserCount +
                ", saleSucceedProfit=" + saleSucceedProfit +
                ", saleProfitRatio=" + saleProfitRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
