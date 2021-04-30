package com.cn.bju.spring.bigdataspringboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Auther: ljh
 * @Date: 2019/8/6 17:06
 * @Description:
 */
public class FileUtile {
    private static final Logger log = LoggerFactory.getLogger(FileUtile.class);
    public String fileUploading(MultipartFile file){
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "/home/demo/input/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    public  String download(HttpServletResponse resp, String name, String downloadPath) throws IOException {
//        String filePath = null;
//        try {
//
//            filePath= URLDecoder.decode(downloadPath,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String realPath = "D:" + File.separator + "apache-tomcat-8.5.15" + File.separator + "files";
        String realPath=downloadPath+"/";//项目路径下你存放文件的地方
        String path = realPath + "/"+ name;//加上文件名称
        File file = new File(path);
        if(!file.exists()){
            throw new IOException("文件不存在");
        }
        name = new String(name.getBytes("GBK"), "ISO-8859-1");
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + name);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
