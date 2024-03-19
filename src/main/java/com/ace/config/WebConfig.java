package com.ace.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Classname: WebConfig
 * @Date: 19/3/2024 11:49 pm
 * @Author: garlam
 * @Description:
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(WebConfig.class.getName());

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置CORS（跨域资源共享）配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("http://localhost:8088")
                .allowedOrigins("http://localhost:8090")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

