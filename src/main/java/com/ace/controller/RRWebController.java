package com.ace.controller;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.RRWebEvents;
import com.ace.service.RRWebService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
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

    public RRWebController(RRWebService rrWebService) {
        this.rrWebService = rrWebService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public ModelAndView getPlaybackList() {
        log.info("access /getPlaybackList.html");
        ModelAndView view = super.page("ace/modules/rrweb/list");
        List<RRWebEvents> events = rrWebService.getByHeads();
        // view.addObject("events", events);
        List<RRWebEvents> tmp = new ArrayList<>();
        tmp.add(events.get(0));
        tmp.add(events.get(0));
        tmp.add(events.get(0));
        tmp.add(events.get(0));
        view.addObject("events", tmp);
        return view;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/playback/{playbackId}")
    public ModelAndView playback(@PathVariable String playbackId) {
        log.info("access /playback {}", playbackId);
        ModelAndView view = super.page("ace/modules/rrweb/playback");
        view.addObject("playbackId", playbackId);
        return view;
    }
}

