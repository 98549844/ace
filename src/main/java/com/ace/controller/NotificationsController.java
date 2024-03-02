package com.ace.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: NotificationsController
 * @Date: 29/2/2024 4:51 pm
 * @Author: tnt-garlamau
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class NotificationsController extends CommonController {
    private static final Logger log = LogManager.getLogger(NotificationsController.class.getName());

    @RequestMapping(value = "/notifications.html", method = RequestMethod.GET)
    public ModelAndView notifications() {
        if (!isLogin()) {
            return logout();
        }
        ModelAndView modelAndView = super.page("ace/modules/notifications/notifications");
        return modelAndView;
    }

    @RequestMapping(value = "/push.html", method = RequestMethod.GET)
    public ModelAndView push() {
        if (!isLogin()) {
            return logout();
        }
        ModelAndView modelAndView = super.page("ace/modules/notifications/push");
        return modelAndView;
    }

}

