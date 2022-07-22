package com.restController;

import com.api.AceApi;
import com.config.AceConfig;
import com.report.config.ReportConfig;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    private ReportConfig reportConfig;
    private AceApi aceApi;


    @Autowired
    public AceRestController(AceConfig aceConfig, ReportConfig reportConfig, AceApi aceApi) {
        this.aceConfig = aceConfig;
        this.reportConfig = reportConfig;
        this.aceApi = aceApi;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List getAceProperties() {
        log.info("aceConfig.getName(): " + aceConfig.getName());
        log.info("aceConfig.getVersion():" + aceConfig.getVersion());
        log.info("aceConfig.profile " + aceConfig.getProfile());
        log.info("aceConfig.isSwaggerEnable(): " + aceConfig.isSwaggerEnable());
        log.info("aceConfig.getProfile(): " + aceConfig.getProfile());

        List aceList = new ArrayList();
        aceList.add("Ace Profile: " + aceConfig.getProfile());
        aceList.add(aceConfig.getName());
        aceList.add(aceConfig.getVersion());
        aceList.add(aceConfig.getProfile());
        aceList.add(reportConfig.getUrl());
        aceList.add(reportConfig.getUserName());
        aceList.add(reportConfig.getPassword());

        return aceList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/AceApi")
    public void getAceApi() {
        //test  FeignClient
        int k = 100;
        for (int i = 0; k < 100; i++) {
            //access control not fix, malfunction this FeignClient
            aceApi.getAce();
        }
        List<String> ls = new ArrayList<>();
        ls.add("[ ACE3 ]");
        ls.add("[ ACE API FeignClient ]");
        ls.add("looping times: " + k);

    }

}

