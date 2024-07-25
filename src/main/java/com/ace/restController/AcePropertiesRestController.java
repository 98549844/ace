package com.ace.restController;

import com.ace.config.AceConfig;
import com.ace.config.ReportConfig;
import com.ace.models.common.RespResult;
import com.ace.utilities.ResourcesUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: AceController
 * @Date: 4/5/2021 12:19 上午
 * @Author: garlam
 * @Description:
 */

@CrossOrigin // 解决浏览器禁止ajax请求本地以外的资源, 后端同时在Controller层的类上增加@CrossOrign注解
@RestController
@RequestMapping("/rest/ace")
@Tag(name = "AceProperties")
@EnableConfigurationProperties
public class AcePropertiesRestController {
    private final static Logger log = LogManager.getLogger(AcePropertiesRestController.class.getName());

    private final AceConfig aceConfig;
    private final ReportConfig reportConfig;


    @Autowired
    public AcePropertiesRestController(AceConfig aceConfig, ReportConfig reportConfig) {
        this.aceConfig = aceConfig;
        this.reportConfig = reportConfig;
    }

    @Operation(summary = "get ace properties")
    @RequestMapping(method = RequestMethod.GET, value = "/get.html")
    public RespResult getAceProperties() {
        log.info("aceConfig.getName(): {}", aceConfig.getName());
        log.info("aceConfig.getVersion():{}", aceConfig.getVersion());
        log.info("aceConfig.profile {}", aceConfig.getProfile());
        log.info("aceConfig.isSwaggerEnable(): {}", aceConfig.isSwaggerEnable());
        log.info("aceConfig.getProfile(): {}", aceConfig.getProfile());
        log.info("Springboot Version: {}", SpringBootVersion.getVersion());

        List<String> aceList = new ArrayList<>();
        aceList.add("Ace Profile: " + aceConfig.getProfile());
        aceList.add(aceConfig.getName());
        aceList.add(aceConfig.getVersion());
        aceList.add("SpringBoot version "+SpringBootVersion.getVersion());
        aceList.add(aceConfig.getProfile());
        aceList.add(reportConfig.getUrl());
        aceList.add(reportConfig.getUserName());
        aceList.add(reportConfig.getPassword());

        return RespResult.success(aceList);
    }

    @Operation(summary = "direct get ace properties")
    @RequestMapping(method = RequestMethod.GET, value = "/directGet/{filePath}")
    public RespResult getDirectAceProperties(@PathVariable String filePath) throws IOException {
        return RespResult.success(ResourcesUtil.get(filePath));
    }


}

