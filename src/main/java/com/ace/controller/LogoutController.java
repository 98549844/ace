package com.ace.controller;

import com.ace.constant.Css;
import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


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
    public AjaxResponse doLogout() {
        log.info("logout()");
        logout(getCurrentUser().getUserId(), getDevice());
        String msg = "Logout success";
        Map<String, String> map = new HashMap();
        map.put("msg", msg);
        map.put(Css.css, Css.green);
        return AjaxResponse.success(map);
    }

//    @RequestMapping(value = {"/logout.html","/ace/logout.html", "/ace/**/logout.html"}, method = RequestMethod.GET)
/*    public ModelAndView logout() {
        log.info("logout()");
        if (NullUtil.isNull(success)) {
            logout(getCurrentUser().getUserId(), getDevice());
            return new ModelAndView("ace/success/logout.html");
        }
        ModelAndView modelAndView = new ModelAndView("ace/login.html");
        String msg = "Logout success";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.green);
        return modelAndView;
    }*/


}
