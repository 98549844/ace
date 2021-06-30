package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class LoginController extends CommonController {
    private static Logger log = LogManager.getLogger(LoginController.class.getName());

    @RequestMapping("/login.html")
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        log.info("userName: " + userName);
        log.info("password: " + password);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "User already exist !");
        //用户存在
        modelAndView = super.page("ace/login");

        return modelAndView;
    }


}

