package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.dao.ShopGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/3/23 17:20
 */
@Service
public class ShopGoodsService {


    @Autowired
    ShopGoodsDao shopGoodsDao;

    public List<ShopPutawayGoodsBean> getGoodsNumber(String shopId, String dt) {
        return shopGoodsDao.getGoodsNumber(shopId, dt);
    }

    public List<ShopNewPutAwayBean> getShopNewPutAway(String shopId, String dt) {
        return shopGoodsDao.getShopNewPutAway(shopId, dt);
    }
    public List<ShopGoodsSaleTopBean> getShopGoodsSaleTop(String shopId, String dt) {
        return shopGoodsDao.getShopGoodsSaleTop(shopId, dt);
    }

    public List<ShopGoodsSaleBean> getGoodsSale(String shopId, String dt) {
        return shopGoodsDao.getGoodsSale(shopId, dt);
    }

    public List<ShopSaleSucceedInfoBean> getSaleSucceedInfo(String shopId, String dt) {
        return shopGoodsDao.getSaleSucceedInfo(shopId, dt);
    }
    public List<ShopSaleSucceedInfoBean> getSaleSucceedInfoType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getSaleSucceedInfoType(shopId, dt,orderType);
    }

    public List<ShopGoodsMoneyTopBean> getGoodsMoneyTop(String shopId, String dt) {
        return shopGoodsDao.getGoodsMoneyTop(shopId, dt);
    }

    public List<ShopGoodsProfitTopBean> getGoodsProfitTop(String shopId, String dt) {
        return shopGoodsDao.getGoodsProfitTop(shopId, dt);
    }

    public List<ShopProvinceTopBean> getShopProvinceTop(String shopId, String dt) {
        return shopGoodsDao.getShopProvinceTop(shopId, dt);
    }
    public List<ShopProvinceTopBean> getShopProvinceTypeTop(String shopId, String dt, String orderType) {
        return shopGoodsDao.getShopProvinceTypeTop(shopId,dt,orderType);
    }

    public List<ShopProvinceTopBean> getShopProvince(String shopId, String dt) {
        return shopGoodsDao.getShopProvince(shopId, dt);
    }
    public List<ShopProvinceTopBean> getShopProvinceType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getShopProvinceType(shopId,dt,orderType);
    }

//    public List<GoodsSuccSale> getGoodsSuccSale(String shopId, String dt) {
//        return shopGoodsDao.getGoodsSuccSale(shopId, dt);
//    }
//    public List<GoodsSuccSaleType> getGoodsSuccSaleType(String shopId, String dt, String orderType) {
//        return shopGoodsDao.getGoodsSuccSaleType(shopId, dt,orderType);
//    }

   //划分平台 客户销售分析
    public List<ShopClientSaleBean> getClientSaleType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getClientSaleType(shopId, dt,orderType);
    }
    //客户销售分析
    public List<ShopClientSaleBean> getClientSale(String shopId, String dt) {
        return shopGoodsDao.getClientSale(shopId, dt);
    }

    //客户采购排行榜
    public List<ShopClientSaleTopBean> getClientSaleTop(String shopId, String dt) {
        return shopGoodsDao.getClientSaleTop(shopId, dt);
    }
    public List<ShopClientSaleTopBean> getClientSaleTopType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getClientSaleTopType(shopId, dt,orderType);
    }
    //----------------------退款指标
    public List<ShopRefundInfoBean> getShopRefundInfo(String shopId, String dt) {
        return shopGoodsDao.getShopRefundInfo(shopId, dt);
    }
    public List<ShopRefundInfoBean> getShopRefundInfoType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getShopRefundInfoType(shopId, dt, orderType);
    }
    //理由
    public List<ShopRefundReasonBean> getShopRefundReason(String shopId, String dt) {
        return shopGoodsDao.getShopRefundReason(shopId, dt);
    }
    public List<ShopRefundReasonBean> getShopRefundReasonType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getShopRefundReasonType(shopId, dt, orderType);
    }
    //商品
    public List<ShopRefundSkuBean> getShopRefundSku(String shopId, String dt) {
        return shopGoodsDao.getShopRefundSku(shopId, dt);
    }
    public List<ShopRefundSkuBean> getShopRefundSkuType(String shopId, String dt, String orderType) {
        return shopGoodsDao.getShopRefundSkuType(shopId, dt, orderType);
    }




//    public List<OrderSaleUserBean> getSaleUser(String shopId,String skuId, String dt) {
//        return shopGoodsDao.getSaleUser(shopId,skuId, dt);
//    }
    public List<ShopGoodsPayInfoBean> getPayIndex(String shopId, String skuId, String dt, String type) {
        return shopGoodsDao.getPayIndex(shopId,skuId, dt,type);
    }

    public List<ShopGoodsPayInfoBean> getPayIndexType(String shopId, String skuId, String dt, String type) {
        return shopGoodsDao.getPayIndexType(shopId,skuId, dt,type);
    }


}
