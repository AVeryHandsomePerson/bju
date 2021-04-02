package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.platform.PagerBean;
import com.cn.bju.spring.bigdataspringboot.bean.shop.ResponseData;
import com.cn.bju.spring.bigdataspringboot.service.PaltFormGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 * @Date 2021/3/23 17:06
 */
@Controller
@Slf4j
@CrossOrigin
public class PaltFormGoodsController {
    @Autowired
    private PaltFormGoodsService paltFromGoodsService;

    @RequestMapping("/find_goods_info")
    @ResponseBody
    public PagerBean getGoodsInfo(@RequestParam Map<String, String> param) {
        PagerBean data = new PagerBean();
        String dt = param.getOrDefault("dt", "").toString();
        Integer page = Integer.valueOf(param.get("page"));
        Integer limit = Integer.valueOf(param.get("limit"));
        if (StringUtils.isNotEmpty(dt)) {
            data.setRows(paltFromGoodsService.getGoodsInfo(dt,page,limit));
            data.setCode(1000);
            return data;
        }
        data.setCode(1200);
        return data;
    }
}
