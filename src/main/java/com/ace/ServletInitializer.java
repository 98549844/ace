package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    private static final Logger log = LogManager.getLogger(AceApplication.class.getName());


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        //information link https://pdai.tech/md/spring/springboot/springboot-x-deploy-war.html
        //打war包的时候才需要这个类
        log.info("using for building a war file and deploying it into tomcat !!!");
        return springApplicationBuilder.sources(AceApplication.class);
    }

}
