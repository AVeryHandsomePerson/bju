package com.cn.bju.spring.bigdataspringboot.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * @author ljh
 * @version 1.0
 */
public class Demo {
    private static InputStream in = new Demo().getClass().getClassLoader().getResourceAsStream("dictionaries.properties");
    private static final Properties properties = new Properties();

    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static String driver = "";

    static {
        try {
            properties.load(in);
            in.close();
            url = properties.getProperty("clickhouse_url");
            user = properties.getProperty("clickhouse_user");
            password = properties.getProperty("clickhouse_password");
            driver = properties.getProperty("clickhouse_driver");
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
    public static void main(String[] args) throws SQLException, ParseException {
        JdbcTemplate jdbcTemplate =  new JdbcTemplate(new Demo().createDataSource());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        DateTime dateTime = new DateTime(DateUtils.parseDate(dateString, "yyyy-MM-dd HH:mm:ss"));
        String conditionFields = "2000000027"+","+dateTime.toString("yyyy-MM-dd")+","+"00";


        List<Map<String, Object>> maps = jdbcTemplate.queryForList(
                "select max(hour),sum(money) from(select orderId,shopId,max(totalMoney) as money,hour from ( select orderId,shopId,case when paid = 1 then 0 else totalMoney end as totalMoney, hour from dwd_order_all where shopId = cast (? as Int64) and day= ? and hour > ?) group by orderId,shopId,hour )"
//                "select hour,count(orderId) from(select orderId,shopId,max(totalMoney) as money,hour from ( select orderId,shopId,case when paid = 1 then 0 else totalMoney end as totalMoney, hour from dwd_order_all where cast (? as Int64) and day= ? and hour > ?) group by orderId,shopId,hour ) group by hour order by hour "
                , conditionFields.split(","));

//        System.out.println(jdbcTemplate.queryForList(
////                "select max(hour),sum(money) from(select orderId,shopId,max(totalMoney) as money,hour from ( select orderId,shopId,case when paid = 1 then 0 else totalMoney end as totalMoney, hour from dwd_order_all where shopId = cast (? as Int64) and day= ? and hour= ?) group by orderId,shopId,hour )"
//                "select hour,count(orderId) from(select orderId,shopId,max(totalMoney) as money,hour from ( select orderId,shopId,case when paid = 1 then 0 else totalMoney end as totalMoney, hour from dwd_order_all where cast (? as Int64) and day= ? and hour > ?) group by orderId,shopId,hour ) group by hour"
//                ,conditionFields.split(",")));
        System.out.println(maps);


    }


}
