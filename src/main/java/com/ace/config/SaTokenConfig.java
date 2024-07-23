package com.ace.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.thymeleaf.dialect.SaTokenDialect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/** Thymeleaf 配置 和 sa-token 配置 是独立的
 * @Classname: SaTokenConfig
 * @Date: 12/12/2021 10:31 AM
 * @Author: garlam
 * @Description:
 */

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    private static final Logger log = LogManager.getLogger(SaTokenConfig.class.getName());

    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> {
            try {
                System.out.println("-------- 前端访问path：" + SaHolder.getRequest().getRequestPath());
               // StpUtil.checkLogin();
              //  System.out.println("-------- 此 path 校验成功：" + SaHolder.getRequest().getRequestPath());
            } catch (Exception e) {
                System.out.println("-------- 此 path 校验失败：" + SaHolder.getRequest().getRequestPath());
                throw e;
            }
        })).addPathPatterns("/**");
    }*/

    //SaRouter
    //https://sa-token.cc/doc.html#/use/route-check?id=%e8%b7%af%e7%94%b1%e6%8b%a6%e6%88%aa%e9%89%b4%e6%9d%83
    //springboot
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        // registry.addInterceptor(new SaRouteInterceptor()) //1.30
        // registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())) //after 1.31
        registry.addInterceptor(new SaInterceptor(handler -> StpUtil.checkLogin())) //after 1.31
                //开放登陆,注册 url
                .excludePathPatterns("/ace/login.html",
                                     "/ace/login.html/*/*",
                                     "/ace/logging.html",
                                     "/ace/registration.html",
                                     "/ace/password/reset.html",
                                     "/ace/logout.html",
                                     "/")
                //开放restController
                .excludePathPatterns("/rest/**")
                //开放naiveController
                .excludePathPatterns("/naive/**")
                //开方api
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/pdf/**")
                //.excludePathPatterns( "/assets/**") //url一定不能以assets开头, 不然就所有url都开放
                .excludePathPatterns("/assets/**/*.js",
                                      "/assets/**/*.png",
                                      "/assets/**/*.jpg",
                                      "/assets/**/*.gif",
                                      //"/assets/favicon.ico",
                                      "/favicon.ico",
                                      "/assets/**/*.css",
                                      "/assets/**/*.woff2",
                                      "/assets/**/*.woff",
                                      "/assets/**/*.ttf",
                                      "/assets/**/*.svg",
                                      "/assets/**/*.eot",
                                      "/assets/**/*.swf",
                                      "/assets/**/*.map",
                                      "/assets/images/**")
                //swagger
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/swagger-ui.html/**",
                                     "/swagger-ui/**",
                                     "/csrf",
                                     "/webjars/**",
                                     "/swagger-resources/**",
                                     "/v2/**",
                                     "/v3/**")
                .addPathPatterns("/**");
    }

    // Sa-Token 标签方言 (Thymeleaf版)
    @Bean
    public SaTokenDialect getSaTokenDialect() {
        return new SaTokenDialect();
    }


    //lazy-initialization: true
    //Sa-Token 自动配置入口类 SaBeanInject 被延迟加载了
    //只需要手动指定懒加载排除掉 SaBeanInject 就可以
    /*
    @Bean
    LazyInitializationExcludeFilter integrationLazyInitExcludeFilter() {
        return LazyInitializationExcludeFilter.forBeanTypes(SaBeanInject.class);
    }
    */

    //https://sa-token.cc/doc.html#/plugin/jwt-extend?id=%e5%92%8c-jwt-%e9%9b%86%e6%88%90
    //集成jwt
}

