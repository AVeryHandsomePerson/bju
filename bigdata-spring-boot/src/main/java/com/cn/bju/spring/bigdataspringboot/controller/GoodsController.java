package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.shop.*;
import com.cn.bju.spring.bigdataspringboot.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @Date 2021/3/23 17:06
 */
@Controller
@Slf4j
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getShelves")
    @ResponseBody
    public ResponseData getGoodsShelves(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            data.setData(goodsService.getGoodsNumber(shopId, dt));
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
            data.setData(goodsService.getGoodsSale(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getClientPrice")
    @ResponseBody
    public ResponseData getClientOnPrice(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(goodsService.getClientOnPrice(shopId, dt));
            } else {
                data.setData(goodsService.getClientOnPriceType(shopId, dt, orderType));
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
            data.setData(goodsService.getGoodsMoneyTop(shopId, dt));
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
            data.setData(goodsService.getGoodsProfitTop(shopId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getProvinceTop")
    @ResponseBody
    public ResponseData getGoodsProvinceTop(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(goodsService.getGoodsProvinceTop(shopId, dt));
            } else {
                data.setData(goodsService.getGoodsProvinceTypeTop(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getSalSuc")
    @ResponseBody
    public ResponseData getGoodsSuccessfulSale(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(goodsService.getGoodsSuccSale(shopId, dt));
            } else {
                data.setData(goodsService.getGoodsSuccSaleType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getClientSale")
    @ResponseBody
    public ResponseData getClientSale(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(goodsService.getClientSale(shopId, dt));
            } else {
                data.setData(goodsService.getClientSaleType(shopId, dt, orderType));
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
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt) && StringUtils.isNotEmpty(orderType)) {
            data.setData(goodsService.getClientSaleType(shopId, dt, orderType));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }



    @RequestMapping("/getRefundIndex")
    @ResponseBody
    public ResponseData getRefundIndex(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(goodsService.getRefundIndex(shopId, dt));
            } else {
                data.setData(goodsService.getRefundIndexType(shopId, dt, orderType));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getSaleUser")
    @ResponseBody
    public ResponseData getSaleUser(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String skuId = param.getOrDefault("skuId", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt)&& StringUtils.isNotEmpty(skuId)) {
            data.setData(goodsService.getSaleUser(shopId,skuId, dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getPayIndex")
    @ResponseBody
    public ResponseData getPayIndex(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String dt = param.getOrDefault("dt", "");
        String skuId = param.getOrDefault("skuId", "");
        if (StringUtils.isNotEmpty(shopId) && StringUtils.isNotEmpty(dt) && StringUtils.isNotEmpty(skuId)) {
            data.setData(goodsService.getPayIndex(shopId, skuId,dt));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


}
