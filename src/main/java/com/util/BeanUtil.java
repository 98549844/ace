package com.util;

import com.AceApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BeanUtil {
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


    /**
     * 从spring容器中获取bean和servletContext
     */
    public static void printBeanNameFromContextLoader() {
        BeanUtil.applicationContext = ContextLoader.getCurrentWebApplicationContext();
        String[] beanNames = BeanUtil.applicationContext.getBeanDefinitionNames();
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
     * @return
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
     * ClassPathXmlApplicationContext --从classpath路径加载配置文件
     */
    public Object getBeanByClassPathXmlApplicationContext() {
        log.info("path pattern =>");
        log.info("xxx.xml");
        log.info("classpath:xxx.xml (location => src/main/resources/xxx.xml)");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        printBeanName(ctx);
        return ctx.getBean("beanName");
    }

    /**
     * @param request
     * @return
     */
    public Object getBeanHttpServletRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext(); //arg0.getSession().getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        printBeanName(ctx);
        return ctx.getBean("beanName");

    }


}
