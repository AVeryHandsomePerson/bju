package com.cn.bju.spring.bigdataspringboot.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ljh
 * @version 1.0
 */
public class SqlUtils {

    public String packageSql(String sql) {
        String sqls = "";
        if(sql.contains("where")){
         sqls = sql+" and shop_id = ?";
        }else{
         sqls = sql+" where shop_id = ? and dt between ? and ?";
        }
        return sqls;
    }

    public String packageField(String value,String shopValue,String startTime,String endTime) {
        String conditionValue = "";
        if(StringUtils.isNotEmpty(value))  conditionValue = value+","+shopValue+","+startTime+","+endTime;
        else conditionValue = shopValue+","+startTime+","+endTime;
        return  conditionValue;
    }

}
