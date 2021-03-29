package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:20
 */
@Service
public class GoodsService {


    @Autowired
    GoodsDao goodsDao;

    public List<GoodsShelvesBean> getGoodsNumber(String shopId, String dt) {
        return goodsDao.getGoodsNumber(shopId, dt);
    }

    public List<GoodsSaleBean> getGoodsSale(String shopId, String dt) {
        return goodsDao.getGoodsSale(shopId, dt);
    }

    public List<ClientOnePriceBean> getClientOnPrice(String shopId, String dt) {
        return goodsDao.getClientOnPrice(shopId, dt);
    }
    public List<ClientOnePriceBean> getClientOnPriceType(String shopId, String dt,String orderType) {
        return goodsDao.getClientOnPriceType(shopId, dt,orderType);
    }

    public List<GoodsMoneyTopBean> getGoodsMoneyTop(String shopId, String dt) {
        return goodsDao.getGoodsMoneyTop(shopId, dt);
    }

    public List<GoodsProfitTopBean> getGoodsProfitTop(String shopId, String dt) {
        return goodsDao.getGoodsProfitTop(shopId, dt);
    }

    public List<GoodsProvinceTopBean> getGoodsProvinceTop(String shopId, String dt) {
        return goodsDao.getGoodsProvinceTop(shopId, dt);
    }
    public List<GoodsProvinceTopBean> getGoodsProvinceTypeTop(String shopId, String dt,String orderType) {
        return goodsDao.getGoodsProvinceTypeTop(shopId,dt,orderType);
    }

    public List<GoodsSuccSale> getGoodsSuccSale(String shopId, String dt) {
        return goodsDao.getGoodsSuccSale(shopId, dt);
    }
    public List<GoodsSuccSaleType> getGoodsSuccSaleType(String shopId, String dt, String orderType) {
        return goodsDao.getGoodsSuccSaleType(shopId, dt,orderType);
    }

   //划分平台 客户销售分析
    public List<ClientSaleBean> getClientSaleType(String shopId, String dt, String orderType) {
        return goodsDao.getClientSaleType(shopId, dt,orderType);
    }
    //客户销售分析
    public List<ClientSaleBean> getClientSale(String shopId, String dt) {
        return goodsDao.getClientSale(shopId, dt);
    }
    //客户采购排行榜
    public List<ClientSaleBean> getClientSaleTopType(String shopId, String dt, String orderType) {
        return goodsDao.getClientSaleTopType(shopId, dt,orderType);
    }

    public List<RefundIndexBean> getRefundIndex(String shopId, String dt) {
        return goodsDao.getRefundIndex(shopId, dt);
    }
    public List<RefundIndexBean> getRefundIndexType(String shopId, String dt,String orderType) {
        return goodsDao.getRefundIndexType(shopId, dt, orderType);
    }

    public List<OrderSaleUserBean> getSaleUser(String shopId,String skuId, String dt) {
        return goodsDao.getSaleUser(shopId,skuId, dt);
    }
    public List<PayIndexInfoBean> getPayIndex(String shopId,String skuId, String dt) {
        return goodsDao.getPayIndex(shopId,skuId, dt);
    }


}
