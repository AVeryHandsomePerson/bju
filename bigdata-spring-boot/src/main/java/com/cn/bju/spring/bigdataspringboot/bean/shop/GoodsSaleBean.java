package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:12
 */
public class GoodsSaleBean {

    private Integer shopId;
    private Integer varNumber;
    private Integer saleUserNumber;
    private Integer saleGoodsNumber;
    private String dt;

    public Integer getSaleGoodsNumber() {
        return saleGoodsNumber;
    }


    public void setSaleGoodsNumber(Integer saleGoodsNumber) {
        this.saleGoodsNumber = saleGoodsNumber;
    }

    public Integer getShopId() {
        return shopId;
    }

    public Integer getVarNumber() {
        return varNumber;
    }


    public String getDt() {
        return dt;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setVarNumber(Integer varNumber) {
        this.varNumber = varNumber;
    }


    public void setDt(String dt) {
        this.dt = dt;
    }


    public Integer getSaleUserNumber() {
        return saleUserNumber;
    }

    public void setSaleUserNumber(Integer saleUserNumber) {
        this.saleUserNumber = saleUserNumber;
    }

    @Override
    public String toString() {
        return "GoodsSaleBean{" +
                "shopId=" + shopId +
                ", varNumber=" + varNumber +
                ", saleUserNumber=" + saleUserNumber +
                ", saleGoodsNumber=" + saleGoodsNumber +
                ", dt='" + dt + '\'' +
                '}';
    }

}
