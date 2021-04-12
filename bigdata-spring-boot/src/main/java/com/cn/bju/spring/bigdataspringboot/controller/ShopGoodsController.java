package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.platform.PagerBean;
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
    public ResponseData getGoodsNumber(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getGoodsNumber(param));
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

        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getShopNewPutAway(param));
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

        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getShopGoodsSaleTop(param));
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

        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getGoodsSale(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getSaleSucceedInfo(param));
            } else {
                data.setData(shopGoodsService.getSaleSucceedInfoType(param));
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
        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getGoodsMoneyTop(param));
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
        if (StringUtils.isNotEmpty(shopId)) {
            data.setData(shopGoodsService.getGoodsProfitTop(param));
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
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopProvinceTop(param));
            } else {
                data.setData(shopGoodsService.getShopProvinceTypeTop(param));
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
        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopProvince(param));
            } else {
                data.setData(shopGoodsService.getShopProvinceType(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getClientSale(param));
            } else {
                data.setData(shopGoodsService.getClientSaleType(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getClientSaleTop(param));
            } else {
                data.setData(shopGoodsService.getClientSaleTopType(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundInfo(param));
            } else {
                data.setData(shopGoodsService.getShopRefundInfoType(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundReason(param));
            } else {
                data.setData(shopGoodsService.getShopRefundReasonType(param));
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

        String orderType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId)) {
            if (StringUtils.isEmpty(orderType)) {
                data.setData(shopGoodsService.getShopRefundSku(param));
            } else {
                data.setData(shopGoodsService.getShopRefundSkuType(param));
            }
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

        String skuId = param.getOrDefault("skuId", "");
        String sourctType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) &&
                StringUtils.isNotEmpty(skuId) &&
                StringUtils.isNotEmpty(sourctType)) {
            if (sourctType.equals("all")) {
                data.setData(shopGoodsService.getPayIndex(param));
            } else {
                data.setData(shopGoodsService.getPayIndexType(param));
            }
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }


    @RequestMapping("/puType")
    @ResponseBody
    public ResponseData getShopGoodsPuType(@RequestParam Map<String, String> param) {
        ResponseData data = new ResponseData();
        String shopId = param.getOrDefault("shopId", "");
        String sourctType = param.getOrDefault("type", "");
        if (StringUtils.isNotEmpty(shopId) &&
                StringUtils.isNotEmpty(sourctType)) {
            data.setData(shopGoodsService.getShopGoodsPuType(param));
            data.setCode(1000);
            return data;
        }
        data.setMsg("请检查参数是否为空");
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/puInfo")
    @ResponseBody
    public PagerBean getShopGoodsPuInfo(@RequestParam Map<String, String> param) {
        PagerBean data = new PagerBean();
        Integer page = Integer.valueOf(param.get("page"));
        Integer limit = Integer.valueOf(param.get("limit"));
        String shopId = param.getOrDefault("shopId", "");
        String startTime = param.getOrDefault("startTime", "");
        String endTime = param.getOrDefault("endTime", "");
        if (StringUtils.isNotEmpty(shopId)) {
            data.setRows(shopGoodsService.getShopGoodsPuInfo(shopId, startTime, endTime, page, limit));
            data.setCode(1000);
            return data;
        }
        data.setCode(1200);
        return data;
    }


    @RequestMapping("/getWareHouseInOut")
    @ResponseBody
    public PagerBean getShopWareHouseInOut(@RequestParam Map<String, String> param) {
        PagerBean data = new PagerBean();
        int page = Integer.parseInt(param.getOrDefault("page", "0"));
        int limit = Integer.parseInt(param.getOrDefault("limit", "0"));
        String shopId = param.getOrDefault("shopId", "");
        String sourctType = param.getOrDefault("type", "");
        String startTime = param.getOrDefault("startTime", "");
        String endTime = param.getOrDefault("endTime", "");
        if (StringUtils.isNotEmpty(shopId) &&
                StringUtils.isNotEmpty(sourctType)) {
            if (page != 0
                    && limit != 0) {
                page = page-1;
                data.setRows(shopGoodsService
                        .getShopWareHouseInOutPage(shopId, startTime, endTime, page, limit, sourctType));
                data.setTotal(shopGoodsService.countShopWareHouseInOutPage(param));
                data.setCode(1000);
                return data;
            }
            data.setRows(shopGoodsService.getShopWareHouseInOut(param));
            data.setCode(1000);
            return data;
        }
        data.setCode(1200);
        return data;
    }

    @RequestMapping("/getWareHouseInfo")
    @ResponseBody
    public PagerBean getShopWareHouseInfo(@RequestParam Map<String, String> param) {
        PagerBean data = new PagerBean();
        int page = Integer.parseInt(param.getOrDefault("page", "0"));
        int limit = Integer.parseInt(param.getOrDefault("limit", "0"));
        String shopId = param.getOrDefault("shopId", "");
        String sourctType = param.getOrDefault("type", "");
        String startTime = param.getOrDefault("startTime", "");
        String endTime = param.getOrDefault("endTime", "");
        if (StringUtils.isNotEmpty(shopId) &&
                StringUtils.isNotEmpty(sourctType)) {
            if (page != 0
                    && limit != 0) {
                page = page-1;
                data.setRows(shopGoodsService
                        .getShopWareHouseInfoPage(shopId, startTime, endTime, page, limit, sourctType));
                data.setTotal(shopGoodsService.countShopWareHouseInfoPage(param));
                data.setCode(1000);
                return data;
            }
            data.setRows(shopGoodsService.getShopWareHouseInfo(param));
            data.setCode(1000);
            return data;
        }
        data.setCode(1200);
        return data;
    }

}
