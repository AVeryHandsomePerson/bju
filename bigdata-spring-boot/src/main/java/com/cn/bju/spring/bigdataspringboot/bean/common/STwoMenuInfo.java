package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class STwoMenuInfo {
    int id;
    String menuName;
    String menuTowName;
    String picturePath;
    String url;
    int orderId;
    int status;
    String logo;
    String describe;
    int oneMenuId;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getOneMenuId() {
        return oneMenuId;
    }

    public void setOneMenuId(int oneMenuId) {
        this.oneMenuId = oneMenuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMenuTowName() {
        return menuTowName;
    }

    public void setMenuTowName(String menuTowName) {
        this.menuTowName = menuTowName;
    }

    @Override
    public String toString() {
        return "STwoMenuInfo{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuTowName='" + menuTowName + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", url='" + url + '\'' +
                ", orderId=" + orderId +
                ", status=" + status +
                ", logo='" + logo + '\'' +
                ", describe='" + describe + '\'' +
                ", oneMenuId=" + oneMenuId +
                '}';
    }
}
