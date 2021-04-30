package com.cn.bju.spring.bigdataspringboot.controller;

import com.cn.bju.spring.bigdataspringboot.bean.common.*;
import com.cn.bju.spring.bigdataspringboot.bean.shop.ResponseData;
import com.cn.bju.spring.bigdataspringboot.service.CommonService;
import com.cn.bju.spring.bigdataspringboot.utils.FileUtile;
import com.cn.bju.spring.bigdataspringboot.utils.MysqlUtils;
import com.cn.bju.spring.bigdataspringboot.utils.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ljh
 * @version 1.0
 */
@Controller
@Slf4j
@CrossOrigin
public class FileController {
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        File fileSourcePath = new File("/export/servers/bju/web/picture_path");
        if (!fileSourcePath.exists()) {
            fileSourcePath.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        log.info("上传文件的大小：" + file.getSize());
        log.info("上传文件的类型:" + file.getContentType());
        log.info("上传文件时所用的属性名称【key】：" + file.getName());
        assert originalFilename != null;
        File dest = new File(fileSourcePath,originalFilename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSourcePath+"/"+originalFilename;
    }
}
