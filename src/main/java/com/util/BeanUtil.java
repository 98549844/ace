package com.util;

import com.AceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Component
@Order(99)
public class BeanUtil implements ApplicationContextAware {
    private static final Logger log = LogManager.getLogger(BeanUtil.class.getName());

    public static ApplicationContext applicationContext = null;

    //@Autowired
    // private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public BeanUtil() {
        if (applicationContext == null) {
            log.info("ApplicationContext loaded from ACE !");
            applicationContext = AceApplication.applicationContext;
        }
    }


    /**
     * print all bean name
     *
     * @param applicationContext
     */
    public static void printBeanName(ApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("total bean: {}", applicationContext.getBeanDefinitionCount());
        // String[] beanNames = applicationContext.getBeanNamesForAnnotation(RequestMapping.class);//所有添加该注解的bean
        int i = 0;
        for (String s : beanNames) {
            log.info("{},beanName: {}", ++i, s);
        }
    }

    public static List<String> getBeanNameList(ApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("total bean: {}", applicationContext.getBeanDefinitionCount());
        // String[] beanNames = applicationContext.getBeanNamesForAnnotation(RequestMapping.class);//所有添加该注解的bean
        List<String> ls = new ArrayList<>();
        for (String s : beanNames) {
            ls.add(s);
        }
        return ls;
    }

    public static List<String> getBeanNameList() {
        BeanUtil.applicationContext = ContextLoader.getCurrentWebApplicationContext();
        List<String> ls = new ArrayList<>();
        if (NullUtil.isNull(BeanUtil.applicationContext)) {
            log.info("ApplicationContext is null");
            return ls;
        }
        String[] beanNames = BeanUtil.applicationContext.getBeanDefinitionNames();
        log.info("total bean: {}", applicationContext.getBeanDefinitionCount());
        // String[] beanNames = applicationContext.getBeanNamesForAnnotation(RequestMapping.class);//所有添加该注解的bean
        for (String s : beanNames) {
            ls.add(s);
        }
        return ls;
    }

    /**
     * 从spring容器中获取bean和servletContext
     */
    public static void printBeanNameFromContextLoader() {
        BeanUtil.applicationContext = ContextLoader.getCurrentWebApplicationContext();
        String[] beanNames = BeanUtil.applicationContext.getBeanDefinitionNames();
        if (NullUtil.isNull(BeanUtil.applicationContext)) {
            log.info("ApplicationContext is null");
            return;
        }
        log.info("total bean: {}", applicationContext.getBeanDefinitionCount());
        // String[] beanNames = applicationContext.getBeanNamesForAnnotation(RequestMapping.class);//所有添加该注解的bean
        int i = 0;
        for (String s : beanNames) {
            log.info("{},beanName: {}", ++i, s);
        }
    }


    /**
     * getBean By beanName
     *
     * @param name
     */
    public Object getBeanByName(String name) {
        Object object = applicationContext.getBean(name);
        return object;
    }


    /**
     * 加载ApplicationContext
     *
     * @param
     */
    public static void getBeanFileSystemXmlApplicationContext() {
        log.info("path pattern =>");
        //第一种: FileSystemXmlApplicationContext
        //加载单个配置文件
        log.info("src/main/resources/xxx.xml");
        ApplicationContext ctx1 = new FileSystemXmlApplicationContext("bean.xml");
        //加载单个配置文件
        String[] locations = {"bean1.xml", "bean2.xml", "bean3.xml"};
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext(locations);
        //根据具体路径加载文件
        log.info("Absolute path => C:/xxx/xxx/xxx/xxx.xml");
        ApplicationContext ctx3 = new FileSystemXmlApplicationContext("C:/project/application_xxx.xml");

    }


    /**
     * 透过ClassPathXmlApplicationContext获取bean name
     * ClassPathXmlApplicationContext --从classpath路径加载配置文件
     */
    public Object getBeanByClassPathXmlApplicationContext() {
        log.info("path pattern =>");
        log.info("xxx.xml");
        log.info("classpath:xxx.xml (location => src/main/resources/xxx.xml)");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:logback.xml");
        printBeanName(ctx);
        return ctx.getBean("beanName");
    }


    /**
     * 透过HttpServletRequest获取bean name
     *
     * @param request
     * @return
     */
    public Object getBeanByHttpServletRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext(); //arg0.getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        printBeanName(ctx);
        return ctx.getBean("beanName");

    }


    /**
     * 透过ApplicationContextAware获取bean name
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            if (BeanUtil.applicationContext == null) {
                BeanUtil.applicationContext = applicationContext;
            }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
