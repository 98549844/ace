package com.ace.restController;

import com.ace.utils.ApplicationContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname: SystemRestController
 * @Date: 7/7/24 PM3:51
 * @Author: garlam
 * @Description:
 */


@CrossOrigin
@RestController
@RequestMapping("/rest/system")
@Tag(name = "系统控制器")
public class SystemRestController {
    private static final Logger log = LogManager.getLogger(SystemRestController.class.getName());


    //@Operation(summary = "restart")
    //@RequestMapping(method = RequestMethod.GET, value = "/restart")
    //public void restart() {
    //
    //}

    @Operation(summary = "shutdown")
    @RequestMapping(method = RequestMethod.GET, value = "/shutdown")
    public void shutdown() {
        try {
            SpringApplication.exit(ApplicationContextUtil.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}

