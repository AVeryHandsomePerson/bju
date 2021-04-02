package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 10:07
 */
public class ShopClientSaleBean {
    private Integer shopId;
    private Integer userDisNumber;
    private Integer presentUserDisNumber;
    private Integer agedUserDisNumber;
    private Integer newUserDisNumber;
    private Integer typeUserRatio;
    private Integer newUserRatio;
    private Integer agedUserRatio;
    private String dt;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserDisNumber() {
        return userDisNumber;
    }

    public void setUserDisNumber(Integer userDisNumber) {
        this.userDisNumber = userDisNumber;
    }

    public Integer getPresentUserDisNumber() {
        return presentUserDisNumber;
    }

    public void setPresentUserDisNumber(Integer presentUserDisNumber) {
        this.presentUserDisNumber = presentUserDisNumber;
    }

    public Integer getAgedUserDisNumber() {
        return agedUserDisNumber;
    }

    public void setAgedUserDisNumber(Integer agedUserDisNumber) {
        this.agedUserDisNumber = agedUserDisNumber;
    }

    public Integer getNewUserDisNumber() {
        return newUserDisNumber;
    }

    public void setNewUserDisNumber(Integer newUserDisNumber) {
        this.newUserDisNumber = newUserDisNumber;
    }

    public Integer getTypeUserRatio() {
        return typeUserRatio;
    }

    public void setTypeUserRatio(Integer typeUserRatio) {
        this.typeUserRatio = typeUserRatio;
    }

    public Integer getNewUserRatio() {
        return newUserRatio;
    }

    public void setNewUserRatio(Integer newUserRatio) {
        this.newUserRatio = newUserRatio;
    }

    public Integer getAgedUserRatio() {
        return agedUserRatio;
    }

    public void setAgedUserRatio(Integer agedUserRatio) {
        this.agedUserRatio = agedUserRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "ClientSaleBean{" +
                "shopId=" + shopId +
                ", userDisNumber=" + userDisNumber +
                ", presentUserDisNumber=" + presentUserDisNumber +
                ", agedUserDisNumber=" + agedUserDisNumber +
                ", newUserDisNumber=" + newUserDisNumber +
                ", typeUserRatio=" + typeUserRatio +
                ", newUserRatio=" + newUserRatio +
                ", agedUserRatio=" + agedUserRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
