package com.ace.controller;

import com.ace.constant.Css;
import com.ace.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: LogoutController
 * @Date: 11/12/2021 6:52 AM
 * @Author: garlam
 * @Description:
 */

@RestController
public class LogoutController extends CommonController {
    private static final Logger log = LogManager.getLogger(LogoutController.class.getName());

    @RequestMapping(value = {"/logout.html", "/ace/logout.html", "/ace/**/logout.html"}, method = RequestMethod.GET)
    public ModelAndView logout() {
        if (isLogin()) {
            log.info("logging out !");
            logout(getCurrentUser().getUserId(), getDevice());
            return null;
        }
        ModelAndView modelAndView = super.page("ace/login.html");
        String msg = "Logout success";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.green);
        log.info(msg);
        return modelAndView;
    }


    /*
    ajax logout 异步提交拆成两个方法
    @RequestMapping(value = "/ace/logout.html", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("ace/login.html");
        String msg = "Logout success";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.green);
        log.info("{} !", msg);
        return modelAndView;
    }

    @RequestMapping(value = {"/rest/logout.html"}, method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse loggingOut() {
        log.info("logging out !");
        logout(getCurrentUser().getUserId(), getDevice());
        return AjaxResponse.success(true);
    }
    */
}
