package com.ace.restController.naive;

import com.ace.models.common.RespResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname: LoginNaiveController
 * @Date: 15/3/2024 10:20 pm
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/naive/")
@Tag(name = "Naive Login")
public class LoginNaiveController extends CommonController {
    private static final Logger log = LogManager.getLogger(LoginNaiveController.class.getName());


    @Operation(summary = "login")
    @RequestMapping(value = {"/ace/login.html", "/"}, method = RequestMethod.GET)
    public RespResult login() {
        log.info("access naive/ace/login.html" );
        return RespResult.success("true");

    }

    @Operation(summary = "process logging")
    @RequestMapping(value = {"/ace/logging.html", "/"}, method = RequestMethod.POST)
    public RespResult logging() {
        log.info("access naive/ace/logging.html" );


        return RespResult.success("true");
    }

}

