package com.cn.bju.spring.bigdataspringboot.dao;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
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
public interface FunctionDao {
    //查询在架品商品
    List<ShopPutawayGoodsBean> getGoodsNumber(String shopId, String dt);
}
