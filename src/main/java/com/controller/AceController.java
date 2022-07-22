package com.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.api.AceApi;
import com.controller.common.CommonController;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


    private AceApi aceApi;

    public AceController(AceApi aceApi) {
        this.aceApi = aceApi;
    }

    @GetMapping(value = "/get/AceApi")
    public ModelAndView getAceApi() {
        Users user = (Users) StpUtil.getSession().get("user");
        log.info("userId: {} ; userAccount: {} ; userName: {}", user.getUserId(), user.getUserAccount(), user.getUsername());
        boolean isLogin = isLogin();
        if (!isLogin) {
            log.info("system logout");
            return logOut();
        }
        for (int i = 0; i < 100; i++) {
            //access control not fix, malfunction this FeignClient
            aceApi.getAce();
        }
        ModelAndView modelAndView = super.page("ace/index");
        return modelAndView;
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index() {
        if (!isLogin()) {
            return logOut();
        }
        ModelAndView modelAndView = super.page("ace/index");
        return modelAndView;
    }

    @RequestMapping(value = "/elements.html", method = RequestMethod.GET)
    public ModelAndView elements() {
        ModelAndView modelAndView = super.page("ace/tool-pages/elements");
        return modelAndView;
    }

    @RequestMapping(value = "/buttons.html", method = RequestMethod.GET)
    public ModelAndView buttons() {
        ModelAndView modelAndView = super.page("ace/tool-pages/buttons");
        return modelAndView;
    }

    @RequestMapping(value = "/blank.html", method = RequestMethod.GET)
    public ModelAndView blank() {
        log.info("access ace/blank.html");
        ModelAndView modelAndView = super.page("ace/blank");
        return modelAndView;
    }


}

