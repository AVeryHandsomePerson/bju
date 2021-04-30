package com.cn.bju.spring.bigdataspringboot.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ljh
 * @version 1.0
 */
public class DictionariesMysqlUtiles {
    private static InputStream in = new DictionariesMysqlUtiles().getClass().getClassLoader().getResourceAsStream("dictionaries.properties");
    private static final Properties properties = new Properties();

    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static String driver = "";

    static {
        try {
            properties.load(in);
            in.close();
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DruidDataSource createDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(3);
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setFilters("stat");
        return dataSource;
    }
}
