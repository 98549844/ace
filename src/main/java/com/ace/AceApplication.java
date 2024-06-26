package com.ace;

import cn.dev33.satoken.SaManager;
import com.ace.config.AceConfig;
import com.ace.config.BrowserConfig;
import com.ace.constant.AceEnvironment;
import com.ace.utilities.GetterSetterUtil;
import com.ace.utilities.MapUtil;
import com.ace.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Indexed;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;


/*
@SpringBootConfiguration
        读取配置文件, 配置文件的路径是当前根目录(src/main/resources/application.yml等)
@EnableAutoConfiguration
        开启自动配置, 扫描当前的所有依赖的jar包, 发现新的依赖出现将会将会根据依赖完各种自动配置
        (扫描start_web, 自动配置内置tomcat默认路径、端口; 依赖了rabbitmq, 自动配置rabbit template)
@ComponentScan
        属于Spring框架(@Component,@Service,@Controller,@Repository,@Entity), 扫描范围默认情况下是启动类坐在的同名包及其子孙包
*/
@SpringBootApplication
@ComponentScan({"com", "com.ace.utils"}) //扫描当前包及其子包
@MapperScan("com.ace.mapper") //扫描mapper接口
@EnableTransactionManagement //开启事务管理
@EnableCaching //开启缓存
@EnableJpaAuditing //for baseEntity using
@EnableAsync //开启异步调用
@Indexed // 为要注入的bean进行index索引
@EnableAspectJAutoProxy // 相当xml配置 <aop:aspectj-autoproxy/>
public class AceApplication {
    private static final Logger log = LogManager.getLogger(AceApplication.class.getName());
    public static ApplicationContext applicationContext;


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        // applicationContext = SpringApplication.run(AceApplication.class, args);
        SpringApplication application = new SpringApplication(AceApplication.class); //实例化application, 内含丰富多功能提供调用
        applicationContext = application.run(AceApplication.class, args); //run application并付值给applicationContext上下文

        //  print all loaded BeanName
        //Console.println(SystemUtil.LINE, Console.BOLD, Console.MAGENTA);
        //BeanUtil.getBeanNames(applicationContext);
        //Console.println(SystemUtil.LINE, Console.BOLD, Console.MAGENTA);

        //  print all loaded properties
        //PropertiesUtil.getLoadedProperties();
        //Console.println(SystemUtil.LINE, Console.BOLD, Console.MAGENTA);

        //iterate bean value by name
        //print server side information
        BeanUtil beanUtil = applicationContext.getBean("beanUtil", BeanUtil.class);
        IpUtil ip = beanUtil.getBeanByName("ipUtil", IpUtil.class);
        MapUtil.iterateMapKeySet(ip.getHostInfo());

        AceConfig aceConfig = beanUtil.getBeanByName("aceConfig", AceConfig.class);
        if (!AceConfig.DOCKER.equals(aceConfig.getProfile())) {
            //Environment is docker, dont open swagger/docHtml page
            //决定项目启动时, 是否主动打开swagger/docHtml
            BrowserConfig browserConfig = new BrowserConfig();
            browserConfig.openAceIndexAndSwagger(aceConfig.isIndexEnable(), aceConfig.isSwaggerEnable(), aceConfig.isDocHtmlEnabled());
            Commands.versionCheck();
            // browserConfig.getCss();
            // browserConfig.getIndex();
        }
        System.out.println("Ace Profile: " + aceConfig.getProfile());
        log.info("Running success: Sa-Token config: {}", SaManager.getConfig());
        System.out.println("AceEnvironment文件储存路径 =>");
        AceEnvironment environment = ApplicationContextUtil.getBean(AceEnvironment.class);
        GetterSetterUtil.getterValue(environment);
        long result = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Ace Application 启动用时: " + result + " 秒");
    }

}
