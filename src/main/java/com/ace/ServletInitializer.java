package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    private static final Logger log = LogManager.getLogger(AceApplication.class.getName());


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //information link https://blog.csdn.net/qq_28289405/article/details/81279742
        //information link https://blog.csdn.net/qq_43799161/article/details/125315579
        //打war包的时候才需要这个类
        log.info("using for building a war file and deploying it into tomcat !!!");
        return application.sources(AceApplication.class);
    }

}
