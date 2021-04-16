package com.cn.bju.spring.bigdataspringboot.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.cn.bju.spring.bigdataspringboot.bean.common.TDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/4/14 11:16
 */
public class MysqlUtils {

    private static final Logger log = LoggerFactory.getLogger(MysqlUtils.class);


    private static String mySqlDriverClass = "com.mysql.jdbc.Driver";

    private static ConcurrentHashMap<Long, DruidDataSource> cacheDataSource = new ConcurrentHashMap<>();


    public static synchronized JdbcTemplate getMySqlConn(TDataSourceBean ds) throws SQLException {
        if (ds.getId() == null) {
            return null;
        }

        if (cacheDataSource.get(ds.getId()) == null) {
             createDataSource(ds);
        }
        return  new JdbcTemplate(cacheDataSource.get(ds.getId()));
    }

    private static void createDataSource(TDataSourceBean ds) throws SQLException {
        StringBuilder jdbcUrl = new StringBuilder();

        jdbcUrl.append("jdbc:mysql://").append(ds.getHost()).append(":").append(ds.getPort()).append("/").append(ds.getDbName())
                .append("?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(mySqlDriverClass);
        dataSource.setUrl(jdbcUrl.toString());
        dataSource.setPoolPreparedStatements(true);
        dataSource.setUsername(ds.getDbUser());
        dataSource.setPassword(ds.getDbPassWord());
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

        cacheDataSource.put(ds.getId(), dataSource);
    }






}
