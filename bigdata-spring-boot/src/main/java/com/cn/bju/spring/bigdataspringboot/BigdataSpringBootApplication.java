package com.cn.bju.spring.bigdataspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cn.bju.spring.bigdataspringboot.dao")
public class BigdataSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigdataSpringBootApplication.class, args);
    }

}
