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
public interface ShopGoodsDao {
    //查询在架品商品
    List<ShopPutawayGoodsBean> getGoodsNumber(String shopId, String dt);


    //查询商品销量
    List<ShopGoodsSaleTopBean> getShopGoodsSaleTop(String shopId, String dt);

    //查询商品品种数，商品客户数，下单商品件数
    List<ShopGoodsSaleBean> getGoodsSale(String shopId, String dt);

    //客单价
    List<ShopSaleSucceedInfoBean> getSaleSucceedInfo(String shopId, String dt);

    List<ShopSaleSucceedInfoBean> getSaleSucceedInfoType(String shopId, String dt, String orderType);

    //商品金额TOP
    List<ShopGoodsMoneyTopBean> getGoodsMoneyTop(String shopId, String dt);

    //商品利润TOP
    List<ShopGoodsProfitTopBean> getGoodsProfitTop(String shopId, String dt);

    //商品省份TOP all
    List<ShopProvinceTopBean> getShopProvinceTop(String shopId, String dt);

    //type
    List<ShopProvinceTopBean> getShopProvinceTypeTop(String shopId, String dt, String orderType);

    //商品省份分布 all
    List<ShopProvinceTopBean> getShopProvince(String shopId, String dt);

    //商品省份分布 type
    List<ShopProvinceTopBean> getShopProvinceType(String shopId, String dt, String orderType);


    //成交金额=店铺收款金额，成交件数 全平台
//    List<GoodsSuccSale> getGoodsSuccSale(String shopId, String dt);
//
//    //划分平台
//    List<GoodsSuccSaleType> getGoodsSuccSaleType(String shopId, String dt, String orderType);

    //  划分平台 客户销售分析
    List<ShopClientSaleBean> getClientSaleType(String shopId, String dt, String orderType);
    //客户销售分析
    List<ShopClientSaleBean> getClientSale(String shopId, String dt);

    //客户采购排行榜
    List<ShopClientSaleTopBean> getClientSaleTop(String shopId, String dt);
    //客户采购排行榜
    List<ShopClientSaleTopBean> getClientSaleTopType(String shopId, String dt, String orderType);

    //-------------------------------退款指标
    //全平台
    List<ShopRefundInfoBean> getShopRefundInfo(String shopId, String dt);
    //划分平台
    List<ShopRefundInfoBean> getShopRefundInfoType(String shopId, String dt, String orderType);
    //理由
    List<ShopRefundReasonBean> getShopRefundReason(String shopId, String dt);
    List<ShopRefundReasonBean> getShopRefundReasonType(String shopId, String dt, String orderType);
    //商品
    List<ShopRefundSkuBean> getShopRefundSku(String shopId, String dt);
    List<ShopRefundSkuBean> getShopRefundSkuType(String shopId, String dt, String orderType);


    //商品级别下单客户
//    List<OrderSaleUserBean> getSaleUser(String shopId, String skuId, String dt);

    //----------------------商品级别支付指标
    //查询最新上架商品
    List<ShopNewPutAwayBean> getShopNewPutAway(String shopId, String dt);

    List<ShopGoodsPayInfoBean> getPayIndex(String shopId, String skuId, String dt, String type);

    List<ShopGoodsPayInfoBean> getPayIndexType(String shopId, String skuId, String dt, String type);


}
