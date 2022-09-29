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

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index() {
        if (!isLogin()) {
            return logOut();
        }
        ModelAndView modelAndView = super.page("ace/index");
        return modelAndView;
    }

    @RequestMapping(value = "/blank.html", method = RequestMethod.GET)
    public ModelAndView blank() {
        log.info("access ace/blank.html");
        ModelAndView modelAndView = super.page("ace/blank");
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

    @RequestMapping(value = "/grid.html", method = RequestMethod.GET)
    public ModelAndView grid() {
        log.info("access ace/grid.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/grid");
        return modelAndView;
    }

    @RequestMapping(value = "/faq.html", method = RequestMethod.GET)
    public ModelAndView faq() {
        log.info("access ace/faq.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/faq");
        return modelAndView;
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public ModelAndView profile() {
        log.info("access ace/profile.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/inbox.html", method = RequestMethod.GET)
    public ModelAndView inbox() {
        log.info("access ace/inbox.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/inbox");
        return modelAndView;
    }

    @RequestMapping(value = "/pricing.html", method = RequestMethod.GET)
    public ModelAndView pricing() {
        log.info("access ace/pricing.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/pricing");
        return modelAndView;
    }

    @RequestMapping(value = "/invoice.html", method = RequestMethod.GET)
    public ModelAndView invoice() {
        log.info("access ace/invoice.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/invoice");
        return modelAndView;
    }

    @RequestMapping(value = "/timeline.html", method = RequestMethod.GET)
    public ModelAndView timeline() {
        log.info("access ace/timeline.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/timeline");
        return modelAndView;
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public ModelAndView search() {
        log.info("access ace/search.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/search");
        return modelAndView;
    }

    @RequestMapping(value = "/email.html", method = RequestMethod.GET)
    public ModelAndView email() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/email");
        return modelAndView;
    }

    @RequestMapping(value = "/typography.html", method = RequestMethod.GET)
    public ModelAndView typography() {
        log.info("access ace/email.typography");
        ModelAndView modelAndView = super.page("ace/tool-pages/typography");
        return modelAndView;
    }

    @RequestMapping(value = "/content-slider.html", method = RequestMethod.GET)
    public ModelAndView contentSlider() {
        log.info("access ace/content-slider.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/content-slider");
        return modelAndView;
    }

    @RequestMapping(value = "/treeview.html", method = RequestMethod.GET)
    public ModelAndView treeView() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/treeview");
        return modelAndView;
    }

    @RequestMapping(value = "/jquery-ui.html", method = RequestMethod.GET)
    public ModelAndView jqueryUi() {
        log.info("access ace/email.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/jquery-ui");
        return modelAndView;
    }

    @RequestMapping(value = "/nestable-list.html", method = RequestMethod.GET)
    public ModelAndView nestableList() {
        log.info("access ace/nestable-list.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/nestable-list");
        return modelAndView;
    }




//    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
//    public ModelAndView faq() {
//        log.info("access ace/faq.html");
//        ModelAndView modelAndView = super.page("ace/tool-pages/faq");
//        return modelAndView;
//    }

}

