package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class TemplateInfo {
    int id;
    String desSql;
    String graphType;
    String conditionFields;
    String description;
    String operationType;
    int dbId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "TemplateInfo{" +
                "id=" + id +
                ", desSql='" + desSql + '\'' +
                ", graphType='" + graphType + '\'' +
                ", conditionFields='" + conditionFields + '\'' +
                ", description='" + description + '\'' +
                ", operationType='" + operationType + '\'' +
                ", dbId=" + dbId +
                '}';
    }
}
