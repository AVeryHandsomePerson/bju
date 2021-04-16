package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.common.TDataSourceBean;
import com.cn.bju.spring.bigdataspringboot.bean.common.TGraphInfo;
import com.cn.bju.spring.bigdataspringboot.bean.common.TemplateInfo;
import com.cn.bju.spring.bigdataspringboot.bean.platform.PagerBean;
import com.cn.bju.spring.bigdataspringboot.bean.shop.ResponseData;
import com.cn.bju.spring.bigdataspringboot.service.CommonService;
import com.cn.bju.spring.bigdataspringboot.service.PaltFormGoodsService;
import com.cn.bju.spring.bigdataspringboot.utils.MysqlUtils;
import com.cn.bju.spring.bigdataspringboot.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author ljh
 * @version 1.0
 */
@Controller
@Slf4j
@CrossOrigin
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private JdbcTemplate template;

    @RequestMapping("/getJdbcInfo")
    @ResponseBody
    public ResponseData getDataSource(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        List<TDataSourceBean> dataSourceId = commonService.getDataSourceAll(param);
        boolean empty = dataSourceId.isEmpty();
        data.setMsg(empty ? "FAILED" : "SUCCESS");
        data.setData(empty ? "Data is not listed" : dataSourceId);
        data.setCode(empty ? 1200 : 1000);
        return data;
    }

    @RequestMapping("/getData")
    @ResponseBody
    public ResponseData getData(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        TemplateInfo getDataBaseInfo = commonService.getTemplateInfoId(param).stream().findFirst().orElse(null);
        if (getDataBaseInfo == null) {
            data.setCode(1200);
            data.setMsg("未查询到图形的配置信息");
            return data;
        }
        TDataSourceBean dataSource = commonService.getDataSourceId(String.valueOf(getDataBaseInfo.getDbId())).stream().findFirst().orElse(null);
        if (getDataBaseInfo == null) {
            data.setCode(1200);
            data.setMsg("未检测到数据库连接信息，请检查数据源管理中是否配置");
            return data;
        }
        if (dataSource.getDbType().equals("mysql")) {
            try {
                template = MysqlUtils.getMySqlConn(dataSource);
                data.setData(new Query(template, getDataBaseInfo.getDesSql(), param.getOrDefault("condition_fields", "")).invoke());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        return data;
    }

    //获取模板列表
    @RequestMapping("/getTemplateInfo")
    @ResponseBody
    public ResponseData getTemplateInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        data.setData(commonService.getTemplateInfo(param));
        data.setCode(1200);
        return data;
    }

    //插入数据信息
    @RequestMapping("/insertGraphInfo")
    @ResponseBody
    public ResponseData insertGraphInfo(@RequestBody List<TGraphInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertGraphInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //更新数据信息
    @RequestMapping("/upDataGraphInfo")
    @ResponseBody
    public ResponseData upDataGraphInfo(@RequestBody List<TGraphInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upDataGraphInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }
}
