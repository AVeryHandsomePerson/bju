package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class TMenuInfo {
    int id;
    String platform;
    String industry;
    String shopType;
    String shopClassify;
    String describe;
    String oneMenu;
    String twoMenu;
    String threeMenu;
    int orderId;
    String picturePath;
    String menuId;
    int twoOrderId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopClassify() {
        return shopClassify;
    }

    public void setShopClassify(String shopClassify) {
        this.shopClassify = shopClassify;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getOneMenu() {
        return oneMenu;
    }

    public void setOneMenu(String oneMenu) {
        this.oneMenu = oneMenu;
    }

    public String getTwoMenu() {
        return twoMenu;
    }

    public void setTwoMenu(String twoMenu) {
        this.twoMenu = twoMenu;
    }

    public String getThreeMenu() {
        return threeMenu;
    }

    public void setThreeMenu(String threeMenu) {
        this.threeMenu = threeMenu;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public int getTwoOrderId() {
        return twoOrderId;
    }

    public void setTwoOrderId(int twoOrderId) {
        this.twoOrderId = twoOrderId;
    }

    @Override
    public String toString() {
        return "TMenuInfo{" +
                "id=" + id +
                ", platform='" + platform + '\'' +
                ", industry='" + industry + '\'' +
                ", shopType='" + shopType + '\'' +
                ", shopClassify='" + shopClassify + '\'' +
                ", describe='" + describe + '\'' +
                ", oneMenu='" + oneMenu + '\'' +
                ", twoMenu='" + twoMenu + '\'' +
                ", threeMenu='" + threeMenu + '\'' +
                ", orderId=" + orderId +
                ", picturePath='" + picturePath + '\'' +
                ", menuId='" + menuId + '\'' +
                ", twoOrderId=" + twoOrderId +
                '}';
    }
}
