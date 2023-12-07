package com.ace.util;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;


/** 打印监控所有bean注入耗时
 * @Classname: TimeCostBeanPostProcessor
 * @Date: 7/12/2023 3:41 pm
 * @Author: garlam
 * @Description:
 */

//@Component
public class TimeCostBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LogManager.getLogger(TimeCostBeanPostProcessor.class.getName());

    private final Map<String, Long> costMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        costMap.put(beanName, System.currentTimeMillis());
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (costMap.containsKey(beanName)) {
            Long start = costMap.get(beanName);
            long cost  = System.currentTimeMillis() - start;
            if (cost > 0) {
                costMap.put(beanName, cost);
                System.out.println(beanName + "\ttime: " + cost);
            }
        }
        return bean;
    }
}

