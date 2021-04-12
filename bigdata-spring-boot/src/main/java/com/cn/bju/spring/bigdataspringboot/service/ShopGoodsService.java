package com.cn.bju.spring.bigdataspringboot.service;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.dao.ShopGoodsDao;
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
public class ShopGoodsService {


    @Autowired
    ShopGoodsDao shopGoodsDao;

    public List<ShopPutawayGoodsBean> getGoodsNumber(Map<String, String> param) {
        return shopGoodsDao.getGoodsNumber(param);
    }

    public List<ShopNewPutAwayBean> getShopNewPutAway(Map<String, String> param) {
        return shopGoodsDao.getShopNewPutAway(param);
    }

    public List<ShopGoodsSaleTopBean> getShopGoodsSaleTop(Map<String, String> param) {
        return shopGoodsDao.getShopGoodsSaleTop(param);
    }

    public List<ShopGoodsSaleBean> getGoodsSale(Map<String, String> param) {
        return shopGoodsDao.getGoodsSale(param);
    }

    public List<ShopSaleSucceedInfoBean> getSaleSucceedInfo(Map<String, String> param) {
        return shopGoodsDao.getSaleSucceedInfo(param);
    }

    public List<ShopSaleSucceedInfoBean> getSaleSucceedInfoType(Map<String, String> param) {
        return shopGoodsDao.getSaleSucceedInfoType(param);
    }

    public List<ShopGoodsMoneyTopBean> getGoodsMoneyTop(Map<String, String> param) {
        return shopGoodsDao.getGoodsMoneyTop(param);
    }

    public List<ShopGoodsProfitTopBean> getGoodsProfitTop(Map<String, String> param) {
        return shopGoodsDao.getGoodsProfitTop(param);
    }

    public List<ShopProvinceTopBean> getShopProvinceTop(Map<String, String> param) {
        return shopGoodsDao.getShopProvinceTop(param);
    }

    public List<ShopProvinceTopBean> getShopProvinceTypeTop(Map<String, String> param) {
        return shopGoodsDao.getShopProvinceTypeTop(param);
    }

    public List<ShopProvinceTopBean> getShopProvince(Map<String, String> param) {
        return shopGoodsDao.getShopProvince(param);
    }

    public List<ShopProvinceTopBean> getShopProvinceType(Map<String, String> param) {
        return shopGoodsDao.getShopProvinceType(param);
    }

//    public List<GoodsSuccSale> getGoodsSuccSale(String shopId, String dt) {
//        return shopGoodsDao.getGoodsSuccSale(param);
//    }
//    public List<GoodsSuccSaleType> getGoodsSuccSaleType(String shopId, String dt, String orderType) {
//        return shopGoodsDao.getGoodsSuccSaleType(param,orderType);
//    }

    //划分平台 客户销售分析
    public List<ShopClientSaleBean> getClientSaleType(Map<String, String> param) {
        return shopGoodsDao.getClientSaleType(param);
    }

    //客户销售分析
    public List<ShopClientSaleBean> getClientSale(Map<String, String> param) {
        return shopGoodsDao.getClientSale(param);
    }

    //客户采购排行榜
    public List<ShopClientSaleTopBean> getClientSaleTop(Map<String, String> param) {
        return shopGoodsDao.getClientSaleTop(param);
    }

    public List<ShopClientSaleTopBean> getClientSaleTopType(Map<String, String> param) {
        return shopGoodsDao.getClientSaleTopType(param);
    }

    //----------------------退款指标
    public List<ShopRefundInfoBean> getShopRefundInfo(Map<String, String> param) {
        return shopGoodsDao.getShopRefundInfo(param);
    }

    public List<ShopRefundInfoBean> getShopRefundInfoType(Map<String, String> param) {
        return shopGoodsDao.getShopRefundInfoType(param);
    }

    //理由
    public List<ShopRefundReasonBean> getShopRefundReason(Map<String, String> param) {
        return shopGoodsDao.getShopRefundReason(param);
    }

    public List<ShopRefundReasonBean> getShopRefundReasonType(Map<String, String> param) {
        return shopGoodsDao.getShopRefundReasonType(param);
    }

    //商品
    public List<ShopRefundSkuBean> getShopRefundSku(Map<String, String> param) {
        return shopGoodsDao.getShopRefundSku(param);
    }

    public List<ShopRefundSkuBean> getShopRefundSkuType(Map<String, String> param) {
        return shopGoodsDao.getShopRefundSkuType(param);
    }


    //    public List<OrderSaleUserBean> getSaleUser(String shopId,String skuId, String dt) {
//        return shopGoodsDao.getSaleUser(shopId,skuId, dt);
//    }
    public List<ShopGoodsPayInfoBean> getPayIndex(Map<String, String> param) {
        return shopGoodsDao.getPayIndex(param);
    }

    public List<ShopGoodsPayInfoBean> getPayIndexType(Map<String, String> param) {
        return shopGoodsDao.getPayIndexType(param);
    }

    public List<ShopGoodsPuTypeBean> getShopGoodsPuType(Map<String, String> param) {
        return shopGoodsDao.getShopGoodsPuType(param);
    }

    public List<ShopGoodsPuInfoBean> getShopGoodsPuInfo(String shopId, String startTime, String endTime, Integer page, Integer limit) {
        return shopGoodsDao.getShopGoodsPuInfo(shopId, startTime, endTime, page, limit);
    }

    public List<ShopWareHouseInOutBean> getShopWareHouseInOut(Map<String, String> param) {
        return shopGoodsDao.getShopWareHouseInOut(param);
    }
    public List<ShopWareHouseInfoBean> getShopWareHouseInOutPage(String shopId, String startTime, String endTime, Integer page, Integer limit, String type) {
        return shopGoodsDao.getShopWareHouseInOutPage(shopId, startTime, endTime, page, limit, type);
    }
    public Long countShopWareHouseInOutPage(Map<String, String> param) {
        return shopGoodsDao.countShopWareHouseInOutPage(param);
    }

    public List<ShopWareHouseInfoBean> getShopWareHouseInfo(Map<String, String> param) {
        return shopGoodsDao.getShopWareHouseInfo(param);
    }
    public List<ShopWareHouseInfoBean> getShopWareHouseInfoPage(String shopId, String startTime, String endTime, Integer page, Integer limit, String type) {
        return shopGoodsDao.getShopWareHouseInfoPage(shopId, startTime, endTime, page, limit, type);
    }
    public Long countShopWareHouseInfoPage(Map<String, String> param) {
        return shopGoodsDao.countShopWareHouseInfoPage(param);
    }


}
