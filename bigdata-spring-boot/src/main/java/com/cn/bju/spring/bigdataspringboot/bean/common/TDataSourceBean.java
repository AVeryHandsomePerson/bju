package com.cn.bju.spring.bigdataspringboot.bean.common;

import java.util.Date;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/4/14 9:29
 */
public class TDataSourceBean {
    Long id;
    String primary;
    String dbType;
    String host;
    int port;
    String dbName;
    String dbUser;
    String dbPassWord;
    String comment;
//    int         null comment '状态：1 启用，2 禁用，3 删除',


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
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

    public String getDbPassWord() {
        return dbPassWord;
    }

    public void setDbPassWord(String dbPassWord) {
        this.dbPassWord = dbPassWord;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TDataSourceBean{" +
                "id=" + id +
                ", primary='" + primary + '\'' +
                ", dbType='" + dbType + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", dbName='" + dbName + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassWord='" + dbPassWord + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
