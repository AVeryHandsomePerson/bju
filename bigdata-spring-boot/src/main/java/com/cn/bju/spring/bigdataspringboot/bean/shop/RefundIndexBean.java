package com.cn.bju.spring.bigdataspringboot.bean.shop;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/24 18:17
 */
public class RefundIndexBean {

    private Long shopId;
    private Double avgTime;
    private Double refundMoney;
    private Double refundNumber;
    private Double quitRatio;
    private String dt;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Double avgTime) {
        this.avgTime = avgTime;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(Double refundNumber) {
        this.refundNumber = refundNumber;
    }

    public Double getQuitRatio() {
        return quitRatio;
    }

    public void setQuitRatio(Double quitRatio) {
        this.quitRatio = quitRatio;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "RefundIndexBean{" +
                "shopId=" + shopId +
                ", avgTime=" + avgTime +
                ", refundMoney=" + refundMoney +
                ", refundNumber=" + refundNumber +
                ", quitRatio=" + quitRatio +
                ", dt='" + dt + '\'' +
                '}';
    }
}
