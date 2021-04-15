package com.cn.bju.spring.bigdataspringboot.dao;

import com.cn.bju.spring.bigdataspringboot.bean.common.TDataSourceBean;
import com.cn.bju.spring.bigdataspringboot.bean.common.TGraphInfo;
import com.cn.bju.spring.bigdataspringboot.bean.common.TemplateInfo;
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
    List<TDataSourceBean> getDataSourceId(Map<String, String> param);

    //查询图形模板信息
    List<TemplateInfo> getTemplateInfo(Map<String, String> param);

    //插入图形信息
    int insertGraphInfo(List<TGraphInfo> list);

    //更新图形信息
    int upDataGraphInfo(List<TGraphInfo> list);
}
