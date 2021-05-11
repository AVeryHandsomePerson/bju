package com.cn.bju.spring.bigdataspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author dell
 * @Auther: ljh
 * @Date: 2021/1/17 14:53
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static List<String> EXCLUDE_PATH = Arrays
            .asList("/login");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntertor())
                .addPathPatterns("/**")
//                忽略拦截
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
