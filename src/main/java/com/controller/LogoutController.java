package com.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.constant.Constant;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @Classname: LogoutController
 * @Date: 11/12/2021 6:52 AM
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class LogoutController extends CommonController {
    private static final Logger log = LogManager.getLogger(LogoutController.class.getName());


    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = super.page("ace/login.html");
        String msg = "Logout success";
        String msgCss = Constant.green;
        modelAndView.addObject("msg", msg);
        modelAndView.addObject("msgCss", msgCss);

        Users a = getCurrentUser();
        clearSession();
        Users a1 = getCurrentUser();
        StpUtil.logout();

        return modelAndView;
    }

}

