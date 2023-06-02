package com.ace.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @Classname: PropertiesUtil
 * @Date: 2022/8/2 下午 08:21
 * @Author: kalam_au
 * @Description:
 */

@Component
@Order(1)
public class PropertiesUtil {
    private static final Logger log = LogManager.getLogger(PropertiesUtil.class.getName());

    public static Environment environment;

    @Autowired
    public PropertiesUtil(Environment environment) {
        PropertiesUtil.environment = environment;
    }

    public static Map<String, String> getLoadedProperties() {
        Environment env = PropertiesUtil.environment;
        log.info("Start print loaded properties");
        //遍历每个配置来源中的配置项
        int m = 0;
        Map<String, String> result = new HashMap<>();
        for (PropertySource<?> propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                m = m + 1;
                int n = 0;
                for (String name : ((EnumerablePropertySource<?>) propertySource).getPropertyNames()) {
                    n = n + 1;
                    String value = env.getProperty(name);
                    log.info("{}.{} --> key:{}; value:{}", m, n, name, value);
                    result.put(name, value);
                }
            }
        }
        log.info("Complete !!!");
        return result;
    }


}

