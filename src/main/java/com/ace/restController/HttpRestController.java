package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.RespResult;
import com.ace.utils.HttpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: HttpRestController
 * @Date: 2022/8/26 下午 02:51
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/httpInfo")
@Tag(name = "Http")
@EnableConfigurationProperties
public class HttpRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(HttpRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public RespResult httpInfo() {
        return RespResult.success(HttpUtil.requestInfo(getRequest()));
    }


}

