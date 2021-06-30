package com.restController;

import com.config.AceConfig;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: AceController
 * @Date: 4/5/2021 12:19 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/ace")
@Api(tags = "ace")
@EnableConfigurationProperties
public class AceRestController {
    private final static Logger log = LogManager.getLogger(AceRestController.class.getName());


    private AceConfig aceConfig;

    @Autowired
    public AceRestController(AceConfig aceConfig) {
        this.aceConfig = aceConfig;
    }

    // @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getAceProperties() {
        log.info("aceConfig.getName(): " + aceConfig.getName());
        log.info("aceConfig.getVersion():" + aceConfig.getVersion());
        log.info("aceConfig.isSwaggerEnable(): " + aceConfig.isSwaggerEnable());
        return aceConfig.getName() + " " + aceConfig.getVersion();
    }

}

