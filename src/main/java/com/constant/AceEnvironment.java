package com.constant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Classname: AceEnvironment
 * @Date: 19/7/2021 12:26 下午
 * @Author: garlam
 * @Description:
 */

@Component
public class AceEnvironment {
    private static Logger log = LogManager.getLogger(AceEnvironment.class.getName());

    //container of application.yml value
    public static Environment environment;

    @Autowired
    public AceEnvironment(Environment environment) {
        this.environment = environment;
    }
}

