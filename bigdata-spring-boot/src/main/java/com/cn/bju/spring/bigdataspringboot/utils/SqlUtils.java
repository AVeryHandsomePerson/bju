package com.cn.bju.spring.bigdataspringboot.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author ljh
 * @version 1.0
 */
public class SqlUtils {

    public String packageSql(String sql,String conditionValue,String orderBy) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(sql);
        if(StringUtils.isNotEmpty(conditionValue)){
            sqlBuffer.append(" where ");
            String[] split = conditionValue.split(",");
            Arrays.stream(split).map(x -> x.concat(" = ?"))
                    .forEach(x -> sqlBuffer.append(x).append(" and "));
            sqlBuffer.append(" shop_id = ? and dt between ? and ?");
        }else {
            sqlBuffer.append(" where shop_id = ? and dt between ? and ?");
        }

        if(StringUtils.isNotEmpty(orderBy)){
            sqlBuffer.append(" order by ").append(orderBy);
        }
        return sqlBuffer.toString();
    }

    public String packageField(String value,String shopValue,String startTime,String endTime) {
        String conditionValue = "";
        if(StringUtils.isNotEmpty(value))  conditionValue = value+","+shopValue+","+startTime+","+endTime;
        else conditionValue = shopValue+","+startTime+","+endTime;
        return  conditionValue;
    }

}
