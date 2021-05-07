package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname: aceConfig
 * @Date: 4/5/2021 10:53 下午
 * @Author: garlam
 * @Description:
 */

@Component
@PropertySource(value = {"classpath:ace.properties"}, ignoreResourceNotFound = false, encoding = "UTF-8", name = "ace.properties")
public class AceConfig {
    private static Logger log = LogManager.getLogger(AceConfig.class.getName());
    @Value("${ace.name}")
    private String name;
    @Value("${ace.version}")
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

