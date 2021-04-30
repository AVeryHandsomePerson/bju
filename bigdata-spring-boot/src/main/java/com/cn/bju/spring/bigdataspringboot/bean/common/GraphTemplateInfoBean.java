package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class GraphTemplateInfoBean {
    int id;
    String graphInfo;
    String tempName;
    int orderId;
    String groupName;
    String operationUser;
    String createTime;
    String graphName;
    String graphDescribe;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGraphInfo() {
        return graphInfo;
    }

    public void setGraphInfo(String graphInfo) {
        this.graphInfo = graphInfo;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String getGraphDescribe() {
        return graphDescribe;
    }

    public void setGraphDescribe(String graphDescribe) {
        this.graphDescribe = graphDescribe;
    }

    @Override
    public String toString() {
        return "GraphTemplateInfoBean{" +
                "id=" + id +
                ", graphInfo='" + graphInfo + '\'' +
                ", tempName='" + tempName + '\'' +
                ", orderId=" + orderId +
                ", groupName='" + groupName + '\'' +
                ", operationUser='" + operationUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", graphDescribe='" + graphDescribe + '\'' +
                ", graphName='" + graphName + '\'' +
                '}';
    }
}
