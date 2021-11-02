package com;

import com.config.AceConfig;
import com.config.BrowserConfig;
import com.util.ApplicationContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import util.IpUtil;
import util.MapUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;


/*
@SpringBootConfiguration
        读取配置文件，配置文件的路径是当前根目录(src/main/resources/application.yml等)
@EnableAutoConfiguration
        开启自动配置，扫描当前的所有依赖的jar包，发现新的依赖出现将会将会根据依赖完各种自动配置
        (扫描start_web，自动配置内置tomcat默认路径、端口；依赖了rabbitmq，自动配置rabbitTemble)
@ComponetScan
        属于Spring框架(@Component,@Service,@Controller,@Repository,@Entity)，扫描范围默认情况下是启动类坐在的同名包及其子孙包
*/
@SpringBootApplication
@ComponentScan({"com", "util"})
@MapperScan("com.mapper")
@EnableTransactionManagement
@EnableCaching
//for baseEntity using
@EnableJpaAuditing
public class AceApplication {
    public static ApplicationContext applicationContext;

    public static void main(String[] args) throws IOException {
        applicationContext = SpringApplication.run(AceApplication.class, args);

        //iterate bean value by name
        //print server side information
        ApplicationContextUtil app = new ApplicationContextUtil();
        //print host info
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        MapUtil mapUtil = new MapUtil();
        mapUtil.iterateMapKeyset(m);

        //print all application context bean name
        // ApplicationContextUtil.printAllBeanName(applicationContext);

        ApplicationContextUtil applicationContextUtil = new ApplicationContextUtil();
        AceConfig aceConfig = (AceConfig) applicationContextUtil.getBeanByName("aceConfig");

        BrowserConfig browserConfig = new BrowserConfig();
        browserConfig.openAceIndexAndSwagger(aceConfig.isIndexEnable(), aceConfig.isSwaggerEnable());

        // browserConfig.openMacDefaultBrowser(aceConfig.isIndexEnable());
        // browserConfig.openSwaggerOnMac(m, aceConfig.isSwaggerEnable());
        // browserConfig.openKnife4jOnMac(m, aceConfig.isKnife4jEnabled());
        //  browserConfig.getCss();
        //  browserConfig.getIndex();



    }
}
