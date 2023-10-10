package com.ace.controller;

import com.ace.constant.Css;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: LogoutController
 * @Date: 11/12/2021 6:52 AM
 * @Author: garlam
 * @Description:
 */

@Controller
public class LogoutController extends CommonController {
    private static final Logger log = LogManager.getLogger(LogoutController.class.getName());


    @RequestMapping(value = {"/logout.html", "/ace/logout.html", "/ace/**/logout.html"}, method = RequestMethod.GET)
    public ModelAndView logout() {
        logout(getCurrentUser().getUserId(), getDevice());
        ModelAndView modelAndView = new ModelAndView("ace/login.html");
        String msg = "Logout success";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.green);
        return modelAndView;
    }

}

