package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.platform.PaltFormGoodsInfoBean;
import com.cn.bju.spring.bigdataspringboot.dao.PaltFormGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:20
 */
@Service
public class PaltFormGoodsService {


    @Autowired
    PaltFormGoodsDao paltFromGoodsDao;
    public List<PaltFormGoodsInfoBean> getGoodsInfo(String dt,Integer page,Integer limit) {
        return paltFromGoodsDao.getGoodsInfo(dt,page,limit);
    }
}
