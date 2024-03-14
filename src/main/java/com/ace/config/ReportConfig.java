package com.ace.config;

import com.ace.constant.AceEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @Classname: ReportConfig
 * @Date: 18/7/2021 2:29 上午
 * @Author: garlam
 * @Description:
 */


@Component
public class ReportConfig {
    private static final Logger log = LogManager.getLogger(ReportConfig.class.getName());


    private AceEnvironment aceEnvironment;

    public ReportConfig(AceEnvironment aceEnvironment) {
        this.aceEnvironment = aceEnvironment;
    }

    public String getUrl() {
        return aceEnvironment.environment.getProperty("spring.datasource.url");
    }

    public String getUserName() {
        return aceEnvironment.environment.getProperty("spring.datasource.username");
    }

    public String getPassword() {
        return aceEnvironment.environment.getProperty("spring.datasource.password");
    }

    public String getForName() {
        return aceEnvironment.environment.getProperty("spring.datasource.driver-class-name");
    }


}

