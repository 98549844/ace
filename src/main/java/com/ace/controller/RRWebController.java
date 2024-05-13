package com.ace.controller;

import com.ace.controller.common.CommonController;
import com.ace.models.entity.RRWebEvents;
import com.ace.models.entity.Users;
import com.ace.service.RRWebService;
import com.ace.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @Classname: RRWebController
 * @Date: 11/5/24 PM8:24
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RRWebController extends CommonController {
    private static final Logger log = LogManager.getLogger(RRWebController.class.getName());

    private final RRWebService rrWebService;
    private final UsersService usersService;


    public RRWebController(RRWebService rrWebService, UsersService usersService) {
        this.rrWebService = rrWebService;
        this.usersService = usersService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public ModelAndView getPlaybackList() {
        log.info("access /ace/getPlaybackList.html");
        ModelAndView view = super.page("ace/modules/rrweb/list");
        List<RRWebEvents> events = rrWebService.getByHeads();
        view.addObject("events", events);
        return view;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/surveillance.html")
    public ModelAndView surveillance() {
        log.info("access /ace/surveillance.html");
        ModelAndView view = super.page("ace/modules/rrweb/surveillance");
        List<Users> surveillance = usersService.findAll();

        view.addObject("surveillance", surveillance);
        return view;
    }
}

