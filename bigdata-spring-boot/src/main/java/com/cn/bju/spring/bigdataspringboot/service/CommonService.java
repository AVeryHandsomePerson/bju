package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.common.*;
import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.dao.CommonDao;
import com.cn.bju.spring.bigdataspringboot.dao.ShopGoodsDao;
import com.cn.bju.spring.bigdataspringboot.utils.MysqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:20
 */
@Service
public class CommonService {
    private static final Logger log = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    CommonDao commonDao;

    //查询连接地址
    public List<TDataSourceBean> getDataSourceAll(Map<String, String> param) {
        return commonDao.getDataSourceAll(param);
    }

    //根据id查询连接方式
    public List<TDataSourceBean> getDataSourceId(String id) {
        return commonDao.getDataSourceId(id);
    }

    //根据ID获取模板信息
    public List<TemplateInfo> getTemplateInfoId(Map<String, String> param) {
        return commonDao.getTemplateInfoId(param);
    }
    public List<TemplateInfo> getGraphTemplateInfo(Map<String, String> param) {
        return commonDao.getGraphTemplateInfo(param);
    }
    //获取模板列表
    public List<TGraphInfo> getGraphInfo(Map<String, String> param) {
        return commonDao.getGraphInfo(param);
    }
    public List<TGraphInfo> getGraphInfoAll(Map<String, String> param) {
        return commonDao.getGraphInfoAll(param);
    }
    public List<TGraphInfo> getGraphInfoType(Map<String, String> param) {
        return commonDao.getGraphInfoType(param);
    }
    public List<TGraphInfo> getGraphInfoTypeAll(Map<String, String> param) {
        return commonDao.getGraphInfoTypeAll(param);
    }
    //插入数据信息
    public int insertGraphInfo(List<TGraphInfo> departments) {
        try {
            return commonDao.insertGraphInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //更新数据信息
    public int upDataGraphInfo(List<TGraphInfo> departments) {
        try {
            return commonDao.upDataGraphInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

    //删除数据信息
    public int deleteDataGraphInfo(int id) {
        try {
            return commonDao.deleteDataGraphInfo(id);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

    //插入数据库信息
    public int insertDataBaseInfo(List<DataBaseInfoBean> list) {
        try {
            return commonDao.insertDataBaseInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

    //获取数据库信息
    public List<DataBaseInfoBean> getDataBaseInfoAll(Map<String, String> param) {
        return commonDao.getDataBaseInfoAll(param);
    }
    public List<DataBaseInfoBean> getDataBaseInfo(Map<String, String> param) {
        return commonDao.getDataBaseInfo(param);
    }
    //根据数据源名称查询
    public List<DataBaseInfoBean> selectDataBaseName(Map<String, String> param) {
        return commonDao.selectDataBaseName(param);
    }
    public List<DataBaseInfoBean> selectDataBaseNameALL(Map<String, String> param) {
        return commonDao.selectDataBaseNameALL(param);
    }

    //更新数据库信息
    public int upDataBaseInfo(List<DataBaseInfoBean> departments) {
        try {
            return commonDao.upDataBaseInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //删除数据库信息
    public int deleteDataBaseInfo(int id) {
        try {
            int i = commonDao.deleteDataBaseInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }


    //保存图形模板信息
    public int insertGraphTemplateInfo(List<GraphTemplateInfoBean> list) {
        try {
            return commonDao.insertGraphTemplateInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //查询图形模板信息
    public List<GraphTemplateInfoBean> selectGraphTemplateInfo(Map<String, String> param) {
        return commonDao.selectGraphTemplateInfo(param);
    }
    public List<GraphTemplateInfoBean> selectGraphTemplateInfoAll(Map<String, String> param) {
        return commonDao.selectGraphTemplateInfoAll(param);
    }
    public List<GraphTemplateInfoBean> selectGraphTemplateInfoType(Map<String, String> param) {
        return commonDao.selectGraphTemplateInfoType(param);
    }
    public List<GraphTemplateInfoBean> selectGraphTemplateInfoTypeAll(Map<String, String> param) {
        return commonDao.selectGraphTemplateInfoTypeAll(param);
    }

    //更新图形模板信息
    public int upGraphTemplateInfo(List<GraphTemplateInfoBean> departments) {
        try {
            return commonDao.upGraphTemplateInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //删除图形模板信息
    public int deleteGraphTemplateInfo(int id) {
        try {
            int i = commonDao.deleteGraphTemplateInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }


    //增加数据分组
    public int insertDataGroupInfo(List<DataGroupBean> list) {
        try {
            return commonDao.insertDataGroupInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //查询数据分组
    public List<DataGroupBean> selectDataGroupInfo(Map<String, String> param) {
        return commonDao.selectDataGroupInfo(param);
    }
    public List<DataGroupBean> selectDataGroupInfoAll(Map<String, String> param) {
        return commonDao.selectDataGroupInfoAll(param);
    }
    //根据数据源名称查询
    public List<DataGroupBean> selectDataGroupName(Map<String, String> param) {
        return commonDao.selectDataGroupName(param);
    }
    public List<DataGroupBean> selectDataGroupNameALL(Map<String, String> param) {
        return commonDao.selectDataGroupNameALL(param);
    }
    //更新数据分组
    public int upDataGroupInfo(List<DataGroupBean> departments) {
        try {
            return commonDao.upDataGroupInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //删除数据分组
    public int deleteDataGroupInfo(int id) {
        try {
            int i = commonDao.deleteDataGroupInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

    //增加数据分组
    public int insertMenuInfo(List<TMenuInfo> list) {
        try {
            return commonDao.insertMenuInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

    //根据菜单信息
    public  List<TMenuInfo> selectMenuInfo(Map<String, String> param){
            return commonDao.selectMenuInfo(param);
    }
    public  List<TMenuInfo> selectMenuInfoAll(Map<String, String> param){
            return commonDao.selectMenuInfoAll(param);
    }

    public  List<TMenuInfo> selectMenuInfoType(Map<String, String> param){
        return commonDao.selectMenuInfoType(param);
    }
    public  List<TMenuInfo> selectMenuInfoTypeAll(Map<String, String> param){
        return commonDao.selectMenuInfoTypeAll(param);
    }

    //更新数据分组
    public int upMenuInfo(List<TMenuInfo> departments) {
        try {
            return commonDao.upMenuInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //删除数据分组
    public int deleteMenuInfo(int id) {
        try {
            int i = commonDao.deleteMenuInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }



    //店铺一级菜单增加
    public int insertOneMenuInfo(List<SOneMenuInfo> list) {
        try {
            return commonDao.insertOneMenuInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //店铺一级菜单查询
    public List<SOneMenuInfo> selectOneMenuInfo(Map<String, String> param) {
        return commonDao.selectOneMenuInfo(param);
    }
    //店铺一级菜单更新
    public int upOneMenuInfo(List<SOneMenuInfo> departments) {
        try {
            return commonDao.upOneMenuInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //店铺一级菜单删除
    public int deleteOneMenuInfo(int id) {
        try {
            int i = commonDao.deleteOneMenuInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }


    //店铺二级菜单增加
    public int insertTwoMenuInfo(List<STwoMenuInfo> list) {
        try {
            return commonDao.insertTwoMenuInfo(list);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //店铺二级菜单查询
    public List<STwoMenuInfo> selectTwoMenuInfo(Map<String, String> param) {
        return commonDao.selectTwoMenuInfo(param);
    }
    //店铺二级菜单更新
    public int upTwoMenuInfo(List<STwoMenuInfo> departments) {
        try {
            return commonDao.upTwoMenuInfo(departments);
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }
    //店铺二级菜单删除
    public int deleteTwoMenuInfo(int id) {
        try {
            int i = commonDao.deleteTwoMenuInfo(id);
            return i;
        } catch (Exception e) {
            log.info(e.toString());
            return -1;
        }
    }

}
