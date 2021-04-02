package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.service.ShopGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @Date 2021/3/23 17:06
 */
@Controller
@Slf4j
@CrossOrigin
public class ShopGoodsController {
    @Autowired
    private ShopGoodsService shopGoodsService;

    @RequestMapping("/getShelves")
    @ResponseBody
    public ResponseData getGoodsShelves(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getGoodsNumber(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;

    }

    @RequestMapping("/getNewPutAway")
    @ResponseBody
    public ResponseData getShopNewPutAway(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getShopNewPutAway(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;

    }

    @RequestMapping("/getSaleTop")
    @ResponseBody
    public ResponseData getShopGoodsSaleTop(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getShopGoodsSaleTop(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;

    }


    @RequestMapping("/getSale")
    @ResponseBody
    public ResponseData getGoodsSale(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getGoodsSale(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getSaleInfo")
    @ResponseBody
    public ResponseData getSaleSucceedInfo(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getSaleSucceedInfo(shopId, dt));
            } else {
                data.setData(shopGoodsService.getSaleSucceedInfoType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getMoneyTop")
    @ResponseBody
    public ResponseData getGoodsMoneyTop(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getGoodsMoneyTop(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getProfitTop")
    @ResponseBody
    public ResponseData getGoodsProfitTop(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(shopGoodsService.getGoodsProfitTop(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getProvinceTop")
    @ResponseBody
    public ResponseData getShopProvinceTop(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopProvinceTop(shopId, dt));
            } else {
                data.setData(shopGoodsService.getShopProvinceTypeTop(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getProvince")
    @ResponseBody
    public ResponseData getShopProvince(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopProvince(shopId, dt));
            } else {
                data.setData(shopGoodsService.getShopProvinceType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


//    @RequestMapping("/getSalSuc")
//    @ResponseBody
//    public ResponseData getGoodsSuccessfulSale(@RequestParam Map<String, String> param) {
//        ResponseData data = new ResponseData();
//        String shopId = param.getOrDefault("shopId", "");
//        String dt = param.getOrDefault("dt", "");
//        String orderType = param.getOrDefault("type", "");
//        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
//            if (StringUtils.isEmpty(orderType)) {
//                data.setData(shopGoodsService.getGoodsSuccSale(shopId, dt));
//            } else {
//                data.setData(shopGoodsService.getGoodsSuccSaleType(shopId, dt, orderType));
//            }
//            data.setCode(1000);
//            return data;
//        }
//        data.setMsg("请检查参数是否为空");
//        data.setCode(1200);
//        return data;
//    }

    @RequestMapping("/getClientSale")
    @ResponseBody
    public ResponseData getClientSale(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getClientSale(shopId, dt));
            } else {
                data.setData(shopGoodsService.getClientSaleType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getClientSaleTop")
    @ResponseBody
    public ResponseData getClientSaleTopType(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getClientSaleTop(shopId, dt));
            } else {
                data.setData(shopGoodsService.getClientSaleTopType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


    //退款信息
    @RequestMapping("/getRefundInfo")
    @ResponseBody
    public ResponseData getRefundIndex(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundInfo(shopId, dt));
            } else {
                data.setData(shopGoodsService.getShopRefundInfoType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }
    //退款理由
    @RequestMapping("/getRefundReason")
    @ResponseBody
    public ResponseData getRefundReason(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundReason(shopId, dt));
            } else {
                data.setData(shopGoodsService.getShopRefundReasonType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    //退款商品
    @RequestMapping("/getRefundSku")
    @ResponseBody
    public ResponseData getRefundSku(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundSku(shopId, dt));
            } else {
                data.setData(shopGoodsService.getShopRefundSkuType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


//    @RequestMapping("/getSaleUser")
//    @ResponseBody
//    public ResponseData getSaleUser(@RequestParam Map<String, String> param) {
//        ResponseData data = new ResponseData();
//        String shopId = param.getOrDefault("shopId", "");
//        String dt = param.getOrDefault("dt", "");
//        String skuId = param.getOrDefault("skuId", "");
//        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)&& StringUtils.isNotEmpty(skuId)) {
//            data.setData(shopGoodsService.getSaleUser(shopId,skuId, dt));
//            data.setCode(1000);
//            return data;
//        }
//        data.setMsg("请检查参数是否为空");
//        data.setCode(1200);
//        return data;
//    }

    @RequestMapping("/getPayIndex")
    @ResponseBody
    public ResponseData getPayIndex(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String skuId = param.getOrDefault("skuId", "");
        String sourctType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) &&
                StringUtils.isNotEmpty(dt) &&
                StringUtils.isNotEmpty(skuId) &&
                StringUtils.isNotEmpty(sourctType)) {
            if(sourctType.equals("all")) {
                data.setData(shopGoodsService.getPayIndex(shopId, skuId, dt, sourctType));
            }else{
                data.setData(shopGoodsService.getPayIndexType(shopId, skuId, dt, sourctType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


}
