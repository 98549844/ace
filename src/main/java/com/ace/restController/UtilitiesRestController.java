package com.ace.restController;

import com.ace.models.common.RespResult;
import com.ace.utilities.OsUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: UtilitiesRestController
 * @Date: 2022/12/9 下午 11:40
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/utilities")
@Tag(name = "Utilities")
@EnableConfigurationProperties
public class UtilitiesRestController {
    private static final Logger log = LogManager.getLogger(UtilitiesRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET, value = "/getOSInformation")
    public RespResult getOSInformation() {
        OsUtil.getOsInfo();
        String osName = OsUtil.getOsName();
        return RespResult.success(osName);
    }

}

