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
    private static Logger log = LogManager.getLogger(AceRestController.class.getName());

    @Autowired
    AceConfig aceConfig;

    // @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getAce() {
        log.info(aceConfig.getName());
        log.info(aceConfig.getVersion());
        return aceConfig.getName() + " " + aceConfig.getVersion();
    }

}

