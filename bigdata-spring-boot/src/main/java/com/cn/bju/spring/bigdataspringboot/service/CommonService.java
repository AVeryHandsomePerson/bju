package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.common.TDataSourceBean;
import com.cn.bju.spring.bigdataspringboot.bean.common.TGraphInfo;
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

    public List<TDataSourceBean> getDataSourceAll(Map<String, String> param) {
        return commonDao.getDataSourceAll(param);
    }
    public List<TDataSourceBean> getDataSourceId(Map<String, String> param) {
        return commonDao.getDataSourceId(param);
    }

    public int insertGraphInfo(List<TGraphInfo> departments)
    {
        try{
            return commonDao.insertGraphInfo(departments);
        }catch (Exception e )
        {
            log.info(e.toString());
            return -1;
        }
    }
}
