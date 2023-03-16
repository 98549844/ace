package com.ace.service;

import com.ace.constant.Css;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: LoginService
 * @Date: 15/12/2021 6:44 AM
 * @Author: garlam
 * @Description:
 */

@Service
public class LoginService extends CommonController {
    private static final Logger log = LogManager.getLogger(LoginService.class.getName());

    public ModelAndView loginError(String msg) {
        log.error(msg);
        ModelAndView modelAndView = super.page("ace/login.html");
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.red);
        return modelAndView;
    }

}

