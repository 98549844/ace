package com.util;

import com.constant.AceEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Iterator;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import com.util.Console;


/**
 * @Classname: PropertiesUtil
 * @Date: 2022/8/2 下午 08:21
 * @Author: kalam_au
 * @Description:
 */


public class PropertiesUtil {
    private static final Logger log = LogManager.getLogger(PropertiesUtil.class.getName());


    public static void printLoadedProperties() {

        Environment env = AceEnvironment.environment;
        //  int i = 0;

        log.info("Start print loaded properties");
        //遍历每个配置来源中的配置项
        int m = 0;
        for (PropertySource<?> propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                m = m + 1;
                int n = 0;
                for (String name : ((EnumerablePropertySource<?>) propertySource).getPropertyNames()) {
                    n = n + 1;
                    log.info("{}.{}=> Key: {}", m, n, name);
                    log.info("Value: {}", env.getProperty(name));
                }
            }
        }
        log.info("Complete !!!");


    }


    public static void main(String[] args) {
        Console.printConsoleColor();
    }

}

