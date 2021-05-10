package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class TGraphInfo {

    int id ;
    String desSql ;
    String graphType ;
    String conditionFields;
    String description;
    int dbId;
    String operationUser;
    String createTime;
    String dataSource;
    String options;
    String operationType;
    String orderBy;

    public String getDesSql() {
        return desSql;
    }

    public void setDesSql(String desSql) {
        this.desSql = desSql;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }

    public String getConditionFields() {
        return conditionFields;
    }

    public void setConditionFields(String conditionFields) {
        this.conditionFields = conditionFields;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "TGraphInfo{" +
                "id=" + id +
                ", desSql='" + desSql + '\'' +
                ", graphType='" + graphType + '\'' +
                ", conditionFields='" + conditionFields + '\'' +
                ", description='" + description + '\'' +
                ", dbId=" + dbId +
                ", operationUser='" + operationUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", options='" + options + '\'' +
                ", operationType='" + operationType + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
