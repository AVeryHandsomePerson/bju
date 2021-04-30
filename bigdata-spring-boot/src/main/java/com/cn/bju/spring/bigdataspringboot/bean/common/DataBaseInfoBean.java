package com.cn.bju.spring.bigdataspringboot.bean.common;

/**
 * @author ljh
 * @version 1.0
 */
public class DataBaseInfoBean {

    Long id;
    String dbType;
    String host;
    int port;
    String dbName;
    String dbUser;
    String dbPassword;
    String comment;
    String createTime;
    String updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DataBaseInfoBean{" +
                "id=" + id +
                ", dbType='" + dbType + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", dbName='" + dbName + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
