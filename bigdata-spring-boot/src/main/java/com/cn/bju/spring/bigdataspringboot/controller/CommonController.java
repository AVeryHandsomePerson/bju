package com.cn.bju.spring.bigdataspringboot.controller;

import com.bjucloud.sso.dto.TokenRequest;
import com.bjucloud.sso.service.UserService;
import com.bjucloud.usercenter.dto.UserLoginDTO;
import com.cn.bju.spring.bigdataspringboot.bean.common.*;
import com.cn.bju.spring.bigdataspringboot.bean.shop.ResponseData;
import com.cn.bju.spring.bigdataspringboot.service.CommonService;
import com.cn.bju.spring.bigdataspringboot.utils.MysqlUtils;
import com.cn.bju.spring.bigdataspringboot.utils.Query;
import com.cn.bju.spring.bigdataspringboot.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 */
@Controller
@Slf4j
@CrossOrigin
public class CommonController {

    @Resource
    private UserService userService; // 用户
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
        SqlUtils  sqlUtils = new SqlUtils();
        String operationType = param.getOrDefault("operation_type","");
        String shopId = param.getOrDefault("shop_id","");

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
        try {
        if (dataSource.getDbType().equals("mysql") &&
                operationType.equals("1")) {
                template = MysqlUtils.getMySqlConn(dataSource,operationType);
                data.setData(new Query(template,
                        sqlUtils.packageSql(getDataBaseInfo.getDesSql(),getDataBaseInfo.getConditionFields(), getDataBaseInfo.getOrderBy()),
                        sqlUtils.packageField(
                        param.getOrDefault("condition_fields", ""),
                        param.getOrDefault("shop_id", ""),
                        param.getOrDefault("start_time", ""),
                        param.getOrDefault("end_time", "")
                        )).invoke());
        }else{
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(date);
                DateTime dateTime = new DateTime(DateUtils.parseDate(dateString, "yyyy-MM-dd HH:mm:ss"));
                template = MysqlUtils.getMySqlConn(dataSource,operationType);
                String conditionFields = shopId+","+dateTime.toString("yyyy-MM-dd")+","+ "00";
                data.setData(new Query(template,
                        getDataBaseInfo.getDesSql(),
                        conditionFields).invoke());
        }
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

    //获取模板列表
    @RequestMapping("/getGraphInfo")
    @ResponseBody
    public ResponseData getGraphInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        param.put("page", String.valueOf(i));
        if(page.equals("0") && pageSize.equals("0")){
            List<TemplateInfo> graphTemplateInfo = commonService.getGraphTemplateInfo(param);
            boolean empty = graphTemplateInfo.isEmpty();
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : graphTemplateInfo);
        }else if (!param.getOrDefault("limit", "").isEmpty() &&
                !param.getOrDefault("page", "").isEmpty() &&
                param.getOrDefault("description", "").isEmpty() &&
                param.getOrDefault("startTime", "").isEmpty() &&
                param.getOrDefault("dataSource", "").isEmpty()) {
            List<TGraphInfo> graphInfoAll = commonService.getGraphInfoAll(param);
            List<TGraphInfo> graphInfo = commonService.getGraphInfo(param);
            boolean empty = graphInfo.isEmpty();
            data.setTotal(graphInfoAll.size());
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : graphInfo);
        }else {
            List<TGraphInfo> graphInfoTypeAll = commonService.getGraphInfoTypeAll(param);
            List<TGraphInfo> graphInfoType = commonService.getGraphInfoType(param);
            boolean empty = graphInfoType.isEmpty();
            data.setTotal(graphInfoTypeAll.size());
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : graphInfoType);
        }
        return data;
    }

    @RequestMapping("/getGraphId")
    @ResponseBody
    public ResponseData getGraphIdInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        List<TemplateInfo> templateInfoId = commonService.getTemplateInfoId(param);
        boolean empty = templateInfoId.isEmpty();
        data.setMsg(empty ? "DataBase is null" : "SUCCESS");
        data.setCode(1000);
        data.setData(empty ? null : templateInfoId);
        return data;
    }

    //插入数据信息
    @RequestMapping("/insertGraphInfo")
    @ResponseBody
    public ResponseData insertGraphInfo(@RequestBody List<TGraphInfo> param) {
        System.out.println(param);
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
    //删除图形模板信息
    @RequestMapping("/deleteDataGraphInfo")
    @ResponseBody
    public ResponseData deleteDataGraphInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteDataGraphInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //插入数据库信息
    @RequestMapping("/insertDataBaseInfo")
    @ResponseBody
    public ResponseData insertDataBaseInfo(@RequestBody List<DataBaseInfoBean> param) {
        System.out.println(param);
        ResponseData data = new ResponseData();
        int result = commonService.insertDataBaseInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //获取数据库信息
    @RequestMapping("/getDataBaseInfo")
    @ResponseBody
    public ResponseData getDataBaseInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        if (param.getOrDefault("name", "").isEmpty()) {
            int dataInfoSize = commonService.getDataBaseInfoAll(param).size();
            param.put("page", String.valueOf(i));
            data.setTotal(dataInfoSize);
            data.setData(commonService.getDataBaseInfo(param));
            data.setCode(1000);
        } else {
            int dataNameSize = commonService.selectDataBaseNameALL(param).size();
            param.put("page", String.valueOf(i));
            data.setTotal(dataNameSize);
            data.setData(commonService.selectDataBaseName(param));
            data.setCode(1000);
        }
        return data;
    }

    //更新数据信息
    @RequestMapping("/upDataBaseInfo")
    @ResponseBody
    public ResponseData upDataBaseInfo(@RequestBody List<DataBaseInfoBean> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upDataBaseInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //删除数据库信息
    @RequestMapping("/deleteDataBaseInfo")
    @ResponseBody
    public ResponseData deleteDataBaseInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteDataBaseInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //保存图形模板信息
    @RequestMapping("/insertGraphTemInfo")
    @ResponseBody
    public ResponseData insertGraphTemplateInfo(@RequestBody List<GraphTemplateInfoBean> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertGraphTemplateInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //获取图形模板信息
    @RequestMapping("/selectGraphTem")
    @ResponseBody
    public ResponseData selectGraphTemplateInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        param.put("page", String.valueOf(i));
        if (param.getOrDefault("name", "").isEmpty() &&
            param.getOrDefault("startTime", "").isEmpty() &&
            param.getOrDefault("startTime", "").isEmpty()) {
            List<GraphTemplateInfoBean> graphTemplateInfoBeans = commonService.selectGraphTemplateInfoAll(param);
            List<GraphTemplateInfoBean> templateInfoBeans = commonService.selectGraphTemplateInfo(param);
            boolean empty = templateInfoBeans.isEmpty();
            data.setTotal(graphTemplateInfoBeans.size());
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : templateInfoBeans);
        } else {
            List<GraphTemplateInfoBean> graphTemplateInfoBeans = commonService.selectGraphTemplateInfoTypeAll(param);
            List<GraphTemplateInfoBean> templateInfoBeans = commonService.selectGraphTemplateInfoType(param);
            boolean empty = templateInfoBeans.isEmpty();
            data.setTotal(graphTemplateInfoBeans.size());
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : templateInfoBeans);
        }
        return data;
    }

    //更新图形模板信息
    @RequestMapping("/upGraphTemplate")
    @ResponseBody
    public ResponseData upGraphTemplateInfo(@RequestBody List<GraphTemplateInfoBean> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upGraphTemplateInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //删除图形模板信息
    @RequestMapping("/dltGraphTemplate")
    @ResponseBody
    public ResponseData deleteGraphTemplateInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteGraphTemplateInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //保存数据分组
    @RequestMapping("/insertDataGroup")
    @ResponseBody
    public ResponseData insertDataGroupInfo(@RequestBody List<DataGroupBean> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertDataGroupInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //获取数据分组
    @RequestMapping("/selectDataGroup")
    @ResponseBody
    public ResponseData selectDataGroupInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        param.put("page", String.valueOf(i));
        if (param.getOrDefault("name", "").isEmpty() &&
            param.getOrDefault("startTime", "").isEmpty()) {
            List<DataGroupBean> graphTemplateInfoBeans = commonService.selectDataGroupInfoAll(param);
            List<DataGroupBean> templateInfoBeans = commonService.selectDataGroupInfo(param);
            boolean empty = templateInfoBeans.isEmpty();
            data.setTotal(graphTemplateInfoBeans.size());
            data.setData(templateInfoBeans);
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : templateInfoBeans);
        } else {
            List<DataGroupBean> graphTemplateInfoBeans = commonService.selectDataGroupNameALL(param);
            List<DataGroupBean> templateInfoBeans = commonService.selectDataGroupName(param);
            boolean empty = templateInfoBeans.isEmpty();
            data.setTotal(graphTemplateInfoBeans.size());
            data.setData(templateInfoBeans);
            data.setMsg(empty ? "DataBase is null" : "SUCCESS");
            data.setCode(1000);
            data.setData(empty ? null : templateInfoBeans);
        }


        return data;
    }

    //更新数据分组
    @RequestMapping("/upDataGroup")
    @ResponseBody
    public ResponseData upDataGroupInfo(@RequestBody List<DataGroupBean> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upDataGroupInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //删除数据分组
    @RequestMapping("/dltDataGroup")
    @ResponseBody
    public ResponseData deleteDataGroupInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteDataGroupInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //插入菜单信息
    @RequestMapping("/insertMenuInfo")
    @ResponseBody
    public ResponseData insertMenuInfo(@RequestBody List<TMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //获取数据分组
    @RequestMapping("/selectMenuInfo")
    @ResponseBody
    public ResponseData selectMenuInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        param.put("page", String.valueOf(i));
        List<TMenuInfo> tMenuInfos = commonService.selectMenuInfoAll(param);
        List<TMenuInfo> tMenuInfoList = commonService.selectMenuInfo(param);
        boolean empty = tMenuInfoList.isEmpty();
        data.setTotal(tMenuInfos.size());
        data.setData(tMenuInfoList);
        data.setMsg(empty ? "DataBase is null" : "SUCCESS");
        data.setCode(1000);
        data.setData(empty ? null : tMenuInfoList);
        return data;
    }

    //获取数据分组
    @RequestMapping("/selectMenuInfoType")
    @ResponseBody
    public ResponseData selectMenuInfoType(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String page = param.getOrDefault("page", "0");
        String pageSize = param.getOrDefault("limit", "0");
        int i = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
        param.put("page", String.valueOf(i));
        List<TMenuInfo> tMenuInfos = commonService.selectMenuInfoTypeAll(param);
        List<TMenuInfo> tMenuInfoList = commonService.selectMenuInfoType(param);
        boolean empty = tMenuInfoList.isEmpty();
        data.setTotal(tMenuInfos.size());
        data.setData(tMenuInfoList);
        data.setMsg(empty ? "DataBase is null" : "SUCCESS");
        data.setCode(1000);
        data.setData(empty ? null : tMenuInfoList);
        return data;
    }

    //更新数据分组
    @RequestMapping("/upMenuInfo")
    @ResponseBody
    public ResponseData upMenuInfo(@RequestBody List<TMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //删除菜单数据分组
    @RequestMapping("/dltMenuInfo")
    @ResponseBody
    public ResponseData deleteMenuInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteMenuInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }



    //店铺一级菜单增加
    @RequestMapping("/insertOneMenuInfo")
    @ResponseBody
    public ResponseData insertOneMenuInfo(@RequestBody List<SOneMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertOneMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //店铺一级菜单查询
    @RequestMapping("/selectOneMenuInfo")
    @ResponseBody
    public ResponseData selectOneMenuInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        List<SOneMenuInfo> sOneMenuInfos = commonService.selectOneMenuInfo(param);
        boolean empty = sOneMenuInfos.isEmpty();
        data.setData(sOneMenuInfos);
        data.setMsg(empty ? "DataBase is null" : "SUCCESS");
        data.setCode(1000);
        data.setData(empty ? null : sOneMenuInfos);
        return data;
    }

    //店铺一级菜单更新
    @RequestMapping("/upOneMenuInfo")
    @ResponseBody
    public ResponseData upOneMenuInfo(@RequestBody List<SOneMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upOneMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //店铺一级菜单删除
    @RequestMapping("/dltOneMenuInfo")
    @ResponseBody
    public ResponseData deleteOneMenuInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteOneMenuInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //店铺二级菜单增加
    @RequestMapping("/insertTwoMenuInfo")
    @ResponseBody
    public ResponseData insertTwoMenuInfo(@RequestBody List<STwoMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.insertTwoMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }


    //店铺二级菜单查询
    @RequestMapping("/selectTwoMenuInfo")
    @ResponseBody
    public ResponseData selectTwoMenuInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        List<STwoMenuInfo> sTwoMenuInfos = commonService.selectTwoMenuInfo(param);
        boolean empty = sTwoMenuInfos.isEmpty();
        data.setData(sTwoMenuInfos);
        data.setMsg(empty ? "DataBase is null" : "SUCCESS");
        data.setCode(1000);
        data.setData(empty ? null : sTwoMenuInfos);
        return data;
    }

    //店铺二级菜单更新
    @RequestMapping("/upTwoMenuInfo")
    @ResponseBody
    public ResponseData upTwoMenuInfo(@RequestBody List<STwoMenuInfo> param) {
        ResponseData data = new ResponseData();
        int result = commonService.upTwoMenuInfo(param);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    //店铺二级菜单删除
    @RequestMapping("/dltTwoMenuInfo")
    @ResponseBody
    public ResponseData deleteTwoMenuInfo(int id) {
        ResponseData data = new ResponseData();
        int result = commonService.deleteTwoMenuInfo(id);
        data.setMsg(result > 0 ? "SUCCESS" : "FAILED");
        data.setCode(result > 0 ? 1000 : 1200);
        return data;
    }

    @ResponseBody
    @RequestMapping("/")
    public String getUserList(@RequestBody TokenRequest userQueryRequest, HttpServletRequest request) {
        UserLoginDTO userLoginDTO = userService.checkToken(userQueryRequest);
        HttpSession session = request.getSession();
        session.setAttribute("token",userQueryRequest);
        session.setAttribute("userLogin",userLoginDTO);
        return "";
    }

}
