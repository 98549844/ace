package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: AceController
 * @Date: 1/7/2021 4:33 上午
 * @Author: garlam
 * @Description:
 */


@Controller
@RequestMapping("/ace")
public class AceController extends CommonController {
    private static Logger log = LogManager.getLogger(AceController.class.getName());


    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = super.page("ace/index");
        return modelAndView;
    }

    @RequestMapping(value = "/404.html", method = RequestMethod.GET)
    public ModelAndView error404() {
        ModelAndView modelAndView = super.page("ace/error404");
        return modelAndView;
    }

    @RequestMapping(value = "/500.html", method = RequestMethod.GET)
    public ModelAndView error500() {
        ModelAndView modelAndView = super.page("ace/error500");
        return modelAndView;
    }

    @RequestMapping(value = "/blank.html", method = RequestMethod.GET)
    public ModelAndView blank() {
        ModelAndView modelAndView = super.page("ace/blank");
        return modelAndView;
    }


}

