package com.cn.bju.spring.bigdataspringboot.dao;

import com.cn.bju.spring.bigdataspringboot.bean.common.*;
import com.cn.bju.spring.bigdataspringboot.bean.platform.PaltFormGoodsInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:22
 */
@Mapper
@Repository
public interface CommonDao {
    //查询在架品商品
//    List<PaltFormGoodsInfoBean> getGoodsInfo(String dt,Integer page,Integer limit);

    //查询连接地址
    List<TDataSourceBean> getDataSourceAll(Map<String, String> param);

    //根据id查询连接方式
    List<TDataSourceBean> getDataSourceId(String id);

    //根据ID查询图形模板信息
    List<TemplateInfo> getTemplateInfoId(Map<String, String> param);
    List<TemplateInfo> getGraphTemplateInfo(Map<String, String> param);
    //查询图形模板信息
    List<TGraphInfo> getGraphInfo(Map<String, String> param);
    List<TGraphInfo> getGraphInfoAll(Map<String, String> param);
    List<TGraphInfo> getGraphInfoType(Map<String, String> param);
    List<TGraphInfo> getGraphInfoTypeAll(Map<String, String> param);
    //插入图形信息
    int insertGraphInfo(List<TGraphInfo> list);
    //更新图形信息
    int upDataGraphInfo(List<TGraphInfo> list);
    //删除图形信息
    int deleteDataGraphInfo(int id);


    //插入数据库信息
    int insertDataBaseInfo(List<DataBaseInfoBean> list);
    //查询数据库信息
    List<DataBaseInfoBean> getDataBaseInfoAll(Map<String, String> param);
    List<DataBaseInfoBean> getDataBaseInfo(Map<String, String> param);
    //根据数据源名称查询
    List<DataBaseInfoBean> selectDataBaseName(Map<String, String> param);
    List<DataBaseInfoBean> selectDataBaseNameALL(Map<String, String> param);
    //更新数据库信息
    int upDataBaseInfo(List<DataBaseInfoBean> list);
    //删除数据库信息
    int deleteDataBaseInfo(int id);
    //保存图形模板信息
    int insertGraphTemplateInfo(List<GraphTemplateInfoBean> list);
    //查询图形模板信息
    List<GraphTemplateInfoBean> selectGraphTemplateInfo(Map<String, String> param);
    List<GraphTemplateInfoBean> selectGraphTemplateInfoAll(Map<String, String> param);
    List<GraphTemplateInfoBean> selectGraphTemplateInfoType(Map<String, String> param);
    List<GraphTemplateInfoBean> selectGraphTemplateInfoTypeAll(Map<String, String> param);
    //更新图形模板信息
    int upGraphTemplateInfo(List<GraphTemplateInfoBean> list);
    //删除图形模板信息
    int deleteGraphTemplateInfo(int id);



    //增加数据分组
    int insertDataGroupInfo(List<DataGroupBean> list);
    //查询数据分组
    List<DataGroupBean> selectDataGroupInfo(Map<String, String> param);
    List<DataGroupBean> selectDataGroupInfoAll(Map<String, String> param);

    //根据数据分组名称查询
    List<DataGroupBean> selectDataGroupName(Map<String, String> param);
    List<DataGroupBean> selectDataGroupNameALL(Map<String, String> param);
    //更新数据分组
    int upDataGroupInfo(List<DataGroupBean> list);
    //删除数据分组
    int deleteDataGroupInfo(int id);

    //插入菜单信息
    int insertMenuInfo(List<TMenuInfo> list);


    //根据菜单信息
    List<TMenuInfo> selectMenuInfo(Map<String, String> param);
    List<TMenuInfo> selectMenuInfoAll(Map<String, String> param);

    List<TMenuInfo> selectMenuInfoType(Map<String, String> param);
    List<TMenuInfo> selectMenuInfoTypeAll(Map<String, String> param);

    //更新数据分组
    int upMenuInfo(List<TMenuInfo> list);
    //删除数据分组
    int deleteMenuInfo(int id);

    //店铺一级菜单增加
    int insertOneMenuInfo(List<SOneMenuInfo> list);
    //店铺一级菜单查询
    List<SOneMenuInfo> selectOneMenuInfo(Map<String, String> param);

    //店铺一级菜单更新
    int upOneMenuInfo(List<SOneMenuInfo> list);
    //店铺一级菜单删除
    int deleteOneMenuInfo(int id);


    //店铺二级菜单增加
    int insertTwoMenuInfo(List<STwoMenuInfo> list);
    //店铺二级菜单查询
    List<STwoMenuInfo> selectTwoMenuInfo(Map<String, String> param);

    //店铺二级菜单更新
    int upTwoMenuInfo(List<STwoMenuInfo> list);
    //店铺二级菜单删除
    int deleteTwoMenuInfo(int id);
}
