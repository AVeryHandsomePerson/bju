package com.cn.bju.spring.bigdataspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = "com.cn.bju.spring.bigdataspringboot.dao")
@ImportResource(locations = "classpath:spring-config-dubbo.xml")
public class BigdataSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigdataSpringBootApplication.class, args);
    }

}
