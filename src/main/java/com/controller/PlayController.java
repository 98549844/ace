package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FilesService;
import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname: PlayController
 * @Date: 2022/10/22 上午 02:12
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class PlayController extends CommonController {
    private static final Logger log = LogManager.getLogger(PlayController.class.getName());

    private FilesService filesService;
    private String videoM3u8;
    private final static String indexM3U8 = "index.m3u8";


    @Autowired
    public PlayController(FilesService filesService) {
        this.filesService = filesService;
        this.videoM3u8 = AceEnvironment.getVideoM3u8();

    }

    @RequestMapping(value = "/play.html", method = RequestMethod.GET)
    public ModelAndView accessMedia(@RequestParam(value = "playId") String playId, HttpServletRequest request) {
        log.info("access ace/play.html");
        String requestPlayId = (String) request.getAttribute("playId");
        log.info("requestPlayId: {}", requestPlayId);
        ModelAndView modelAndView = super.page("ace/tool-pages/play");
        modelAndView.addObject("playId", playId);
        return modelAndView;
    }

    /**
     * 开始加载媒体准备播放
     *
     * @param uuid
     * @param response
     * @param
     */
    @RequestMapping(value = "/media/play/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void play(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access ace/play.html uuid: {}", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + indexM3U8;
        log.info("Location: {}", location);
        //ModelAndView modelAndView = super.page("ace/tool-pages/play");
        filesService.get(location, response);
        //return modelAndView;
    }

}

