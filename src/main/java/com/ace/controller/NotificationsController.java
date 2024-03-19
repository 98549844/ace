package com.ace.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


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



    @RequestMapping(value = "/polling.html", method = RequestMethod.GET)
    public ModelAndView polling() {
        if (!isLogin()) {
            return logout();
        }
        ModelAndView modelAndView = super.page("ace/modules/notifications/polling");
        return modelAndView;
    }

    @RequestMapping(value = "/ssePolling.html", method = RequestMethod.GET)
    public ModelAndView ssePolling() {
        if (!isLogin()) {
            return logout();
        }
        ModelAndView modelAndView = super.page("ace/modules/notifications/ssePolling");
        return modelAndView;
    }




}

