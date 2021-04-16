package com.cn.bju.spring.bigdataspringboot.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 */
public class Query {

   private String sql;
   private JdbcTemplate template ;
   private String restrict ;


    public Query(JdbcTemplate jdbcTemplate,String sql,String restrict){
      this.sql = sql;
      this.template = jdbcTemplate;
      this.restrict = restrict;
    }

    public List<Map<String,Object>> invoke() throws SQLException {
        restrict.split(",");
      return  template.queryForList(sql,restrict);
    }


}
