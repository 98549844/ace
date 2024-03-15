package com.ace.restController.naive;

import com.ace.models.common.AjaxResponse;
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
public class LoginNaiveController extends CommonController {
    private static final Logger log = LogManager.getLogger(LoginNaiveController.class.getName());


    @RequestMapping(value = {"/ace/login.html", "/"}, method = RequestMethod.GET)
    public AjaxResponse login() {
        return AjaxResponse.success("true");

    }

    @RequestMapping(value = {"/ace/logging.html", "/"}, method = RequestMethod.POST)
    public AjaxResponse logging() {


        return AjaxResponse.success("true");
    }

}

