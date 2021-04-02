package com.cn.bju.spring.bigdataspringboot.dao;

import com.cn.bju.spring.bigdataspringboot.bean.platform.PaltFormGoodsInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:22
 */
@Mapper
@Repository
public interface PaltFormGoodsDao {
    //查询在架品商品
    List<PaltFormGoodsInfoBean> getGoodsInfo(String dt,Integer page,Integer limit);
}
