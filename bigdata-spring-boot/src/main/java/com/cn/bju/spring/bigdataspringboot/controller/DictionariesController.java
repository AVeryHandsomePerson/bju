package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.common.*;
import com.cn.bju.spring.bigdataspringboot.bean.shop.ResponseData;
import com.cn.bju.spring.bigdataspringboot.service.CommonService;
import com.cn.bju.spring.bigdataspringboot.utils.MysqlUtils;
import com.cn.bju.spring.bigdataspringboot.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 */
@Controller
@Slf4j
@CrossOrigin
public class DictionariesController {
    @Autowired
    private JdbcTemplate template;

    @RequestMapping("/getPlatformData")
    @ResponseBody
    public ResponseData getPlatformData(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String sql = "";
        try {
            if(param.getOrDefault("type","").equals("platform")){
              sql = "select distinct name from plat_industry_rel left join base_dictionary bd on plat_industry_rel.platform = bd.code";
            }else if (param.getOrDefault("type","").equals("industry")){
                sql = "select distinct name from plat_industry_rel left join base_dictionary bd on plat_industry_rel.industry = bd.code";
            }else if (param.getOrDefault("type","").equals("shop_form")){
                sql = "select distinct name from plat_industry_rel left join base_dictionary bd on plat_industry_rel.shop_form = bd.code";
            }
            template = MysqlUtils.getMySqlConn();
//            System.out.println(template.queryForList(sql));
            data.setData(new Query(template, sql, param.getOrDefault("condition_fields", "")).invoke());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return data;
    }

}
