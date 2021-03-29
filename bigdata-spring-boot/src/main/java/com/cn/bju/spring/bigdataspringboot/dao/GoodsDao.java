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
public interface GoodsDao {
    //查询在架品商品
    List<GoodsShelvesBean> getGoodsNumber(String shopId, String dt);

    //查询商品品种数，商品客户数，下单商品件数
    List<GoodsSaleBean> getGoodsSale(String shopId, String dt);

    //客单价
    List<ClientOnePriceBean> getClientOnPrice(String shopId, String dt);

    List<ClientOnePriceBean> getClientOnPriceType(String shopId, String dt, String orderType);

    //商品金额TOP
    List<GoodsMoneyTopBean> getGoodsMoneyTop(String shopId, String dt);

    //商品利润TOP
    List<GoodsProfitTopBean> getGoodsProfitTop(String shopId, String dt);

    //商品省份TOP all
    List<GoodsProvinceTopBean> getGoodsProvinceTop(String shopId, String dt);

    //type
    List<GoodsProvinceTopBean> getGoodsProvinceTypeTop(String shopId, String dt, String orderType);

    //成交金额=店铺收款金额，成交件数 全平台
    List<GoodsSuccSale> getGoodsSuccSale(String shopId, String dt);

    //划分平台
    List<GoodsSuccSaleType> getGoodsSuccSaleType(String shopId, String dt, String orderType);

    //  划分平台 客户销售分析
    List<ClientSaleBean> getClientSaleType(String shopId, String dt, String orderType);

    //客户销售分析
    List<ClientSaleBean> getClientSale(String shopId, String dt);

    //客户采购排行榜
    List<ClientSaleTopBean> getClientSaleTopType(String shopId, String dt, String orderType);


    //全平台
    List<RefundIndexBean> getRefundIndex(String shopId, String dt);

    //划分平台
    List<RefundIndexBean> getRefundIndexType(String shopId, String dt, String orderType);

    //商品级别下单客户
    List<OrderSaleUserBean> getSaleUser(String shopId, String skuId, String dt);

    //商品级别支付指标
    List<PayIndexInfoBean> getPayIndex(String shopId, String skuId, String dt);


}
