package com.ace.utils;

import com.ace.AceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @Classname: ApplicationContextUtil
 * @Date: 2023/1/15 上午 12:15
 * @Author: kalam_au
 * @Description:
 */


public class ApplicationContextUtil {
    private static final Logger log = LogManager.getLogger(ApplicationContextUtil.class.getName());

    public static ApplicationContext getApplicationContext() {
        log.info("get applicationContext from application main method");
        return AceApplication.applicationContext;
    }

    public static ApplicationContext getApplicationContextFromContextLoader() {
        log.info("get applicationContext from ContextLoader.getCurrentWebApplicationContext");
        return ContextLoader.getCurrentWebApplicationContext();
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) AceApplication.applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return AceApplication.applicationContext.getBean(requiredType);
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (AceApplication.applicationContext == null) {
            throw new IllegalStateException("applicationContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }
    }

}

