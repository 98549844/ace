package com.controller;

import com.config.AceConfig;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import util.LogUtil;

/**
 * @Classname: AceController
 * @Date: 4/5/2021 12:19 上午
 * @Author: garlam
 * @Description:
 */

@EnableConfigurationProperties
@RestController
@RequestMapping("/ace")
@Api(tags = "ace")
public class AceRestController {
    private final static Logger log = LogManager.getLogger(AceRestController.class.getName());


    private final AceConfig aceConfig;

    @Autowired
    public AceRestController(AceConfig aceConfig) {
        this.aceConfig = aceConfig;
    }

    // @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getAceProperties() {
        log.info(aceConfig.getName());
        log.info(aceConfig.getVersion());
        return aceConfig.getName() + " " + aceConfig.getVersion();
    }

}

