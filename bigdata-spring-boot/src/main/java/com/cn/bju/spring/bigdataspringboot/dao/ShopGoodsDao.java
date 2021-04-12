package com.cn.bju.spring.bigdataspringboot.dao;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
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
public interface ShopGoodsDao {
    //查询在架品商品
    List<ShopPutawayGoodsBean> getGoodsNumber(Map<String, String> param);


    //查询商品销量
    List<ShopGoodsSaleTopBean> getShopGoodsSaleTop(Map<String, String> param);

    //查询商品品种数，商品客户数，下单商品件数
    List<ShopGoodsSaleBean> getGoodsSale(Map<String, String> param);

    //客单价
    List<ShopSaleSucceedInfoBean> getSaleSucceedInfo(Map<String, String> param);

    List<ShopSaleSucceedInfoBean> getSaleSucceedInfoType(Map<String, String> param);

    //商品金额TOP
    List<ShopGoodsMoneyTopBean> getGoodsMoneyTop(Map<String, String> param);

    //商品利润TOP
    List<ShopGoodsProfitTopBean> getGoodsProfitTop(Map<String, String> param);

    //商品省份TOP all
    List<ShopProvinceTopBean> getShopProvinceTop(Map<String, String> param);

    //type
    List<ShopProvinceTopBean> getShopProvinceTypeTop(Map<String, String> param);

    //商品省份分布 all
    List<ShopProvinceTopBean> getShopProvince(Map<String, String> param);

    //商品省份分布 type
    List<ShopProvinceTopBean> getShopProvinceType(Map<String, String> param);


    //成交金额=店铺收款金额，成交件数 全平台
//    List<GoodsSuccSale> getGoodsSuccSale(Map<String, String> param);
//
//    //划分平台
//    List<GoodsSuccSaleType> getGoodsSuccSaleType(Map<String, String> param, String orderType);

    //  划分平台 客户销售分析
    List<ShopClientSaleBean> getClientSaleType(Map<String, String> param);
    //客户销售分析
    List<ShopClientSaleBean> getClientSale(Map<String, String> param);

    //客户采购排行榜
    List<ShopClientSaleTopBean> getClientSaleTop(Map<String, String> param);
    //客户采购排行榜
    List<ShopClientSaleTopBean> getClientSaleTopType(Map<String, String> param);

    //-------------------------------退款指标
    //全平台
    List<ShopRefundInfoBean> getShopRefundInfo(Map<String, String> param);
    //划分平台
    List<ShopRefundInfoBean> getShopRefundInfoType(Map<String, String> param);
    //理由
    List<ShopRefundReasonBean> getShopRefundReason(Map<String, String> param);
    List<ShopRefundReasonBean> getShopRefundReasonType(Map<String, String> param);
    //商品
    List<ShopRefundSkuBean> getShopRefundSku(Map<String, String> param);
    List<ShopRefundSkuBean> getShopRefundSkuType(Map<String, String> param);


    //商品级别下单客户
//    List<OrderSaleUserBean> getSaleUser(String shopId, String skuId, String dt);

    //----------------------商品级别支付指标
    //查询最新上架商品
    List<ShopNewPutAwayBean> getShopNewPutAway(Map<String, String> param);

    List<ShopGoodsPayInfoBean> getPayIndex(Map<String, String> param);

    List<ShopGoodsPayInfoBean> getPayIndexType(Map<String, String> param);

//    采购统计
    List<ShopGoodsPuTypeBean> getShopGoodsPuType(Map<String, String> param);
    List<ShopGoodsPuInfoBean> getShopGoodsPuInfo(String shopId,String startTime, String endTime,Integer page,Integer limit);

    List<ShopWareHouseInOutBean> getShopWareHouseInOut(Map<String, String> param);
    List<ShopWareHouseInfoBean> getShopWareHouseInOutPage(String shopId,String startTime, String endTime,Integer page,Integer limit,String type);
    Long countShopWareHouseInOutPage(Map<String, String> param);



    List<ShopWareHouseInfoBean> getShopWareHouseInfo(Map<String, String> param);
    List<ShopWareHouseInfoBean> getShopWareHouseInfoPage(String shopId,String startTime, String endTime,Integer page,Integer limit,String type);
    Long countShopWareHouseInfoPage(Map<String, String> param);

}
