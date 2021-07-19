package com.report.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import util.NullUtil;

/**
 * @Classname: ReportConfig
 * @Date: 18/7/2021 2:29 上午
 * @Author: garlam
 * @Description:
 */


@Component
@PropertySource(value = {"classpath:application.yml"}, encoding = "UTF-8", name = "application.yml")
@PropertySource(value = {"classpath:report.properties"}, encoding = "UTF-8", name = "report.properties")
public class ReportConfig {
    private static Logger log = LogManager.getLogger(ReportConfig.class.getName());

    private Environment env;

    @Autowired
    public ReportConfig(Environment environment) {
        this.env = environment;
    }

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${mariadb.driver-class-name}")
    private String forName;


    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        this.url = env.getProperty("spring.datasource.url");
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        this.userName = env.getProperty("spring.datasource.username");
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        this.password = env.getProperty("spring.datasource.password");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }


}

