package com.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Classname: SaTokenConfig
 * @Date: 12/12/2021 10:31 AM
 * @Author: garlam
 * @Description:
 */

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(SaTokenConfig.class.getName());

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/ace/logging.html", "/ace/login.html", "/")
                .excludePathPatterns(
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.jpg",
                        "/favicon.ico",
                        "/**/*.css",
                        "/**/*.woff2",
                        "/**/*.woff",
                        "/**/*.ttf",
                        "/**/*.svg",
                        "/**/*.eot",
                        "/images/**")
                //swagger
                .excludePathPatterns("/doc.html").excludePathPatterns("/swagger-ui.html", "/csrf", "/webjars/**", "/swagger-resources/**", "/v2/**");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
//        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        return passwordEncoder;
    }
}

