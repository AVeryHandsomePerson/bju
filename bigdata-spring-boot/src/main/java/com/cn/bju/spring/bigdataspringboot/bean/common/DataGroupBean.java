package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class DataGroupBean {
    int id;
    String groupName;
    String groupDescribe;
    int orderId;
    String createTime;
    String operationUser;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescribe() {
        return groupDescribe;
    }

    public void setGroupDescribe(String groupDescribe) {
        this.groupDescribe = groupDescribe;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    @Override
    public String toString() {
        return "DataGroupBean{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", groupDescribe='" + groupDescribe + '\'' +
                ", orderId=" + orderId +
                ", createTime='" + createTime + '\'' +
                ", operationUser='" + operationUser + '\'' +
                '}';
    }
}
