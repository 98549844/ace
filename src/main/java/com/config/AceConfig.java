package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import util.NullUtil;

/**
 * @Classname: aceConfig
 * @Date: 4/5/2021 10:53 下午
 * @Author: garlam
 * @Description:
 */

@Component
@PropertySource(value = {"classpath:ace.properties"}, encoding = "UTF-8", name = "ace.properties")
@PropertySource(value = {"classpath:swagger2.properties"}, encoding = "UTF-8", name = "swagger2.properties")
@PropertySource(value = {"classpath:application.yml"}, encoding = "UTF-8", name = "application.yml")
public class AceConfig {
    private static Logger log = LogManager.getLogger(AceConfig.class.getName());
    @Value("${ace.name}")
    private String name;
    @Value("${ace.version}")
    private String version;
    @Value("${swagger.enabled}")
    private boolean swaggerEnable;
    @Value("${knife4j.enabled}")
    private boolean knife4jEnabled;
    @Value("${ace.indexEnable}")
    private boolean indexEnable;
    @Value("${spring.profiles.active}")
    private String profile;


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

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

    public boolean isSwaggerEnable() {
        return swaggerEnable;
    }

    public void setSwaggerEnable(boolean swaggerEnable) {
        this.swaggerEnable = swaggerEnable;
    }

    public boolean isIndexEnable() {
        return indexEnable;
    }

    public void setIndexEnable(boolean indexEnable) {
        this.indexEnable = indexEnable;
    }

    public boolean isKnife4jEnabled() {
        return knife4jEnabled;
    }

    public void setKnife4jEnabled(boolean knife4jEnabled) {
        this.knife4jEnabled = knife4jEnabled;
    }
}

