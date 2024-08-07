package com.ace.controller;

import com.ace.constants.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.service.FilesService;
import com.ace.utilities.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Classname: PlayController
 * @Date: 2022/10/22 上午 02:12
 * @Author: kalam_au
 * @Description:
 */

//@CrossOrigin(origins = "http://192.168.1.100:8088")
@Controller
@RequestMapping("/ace")
public class PlayController extends CommonController {
    private static final Logger log = LogManager.getLogger(PlayController.class.getName());

    private final FilesService filesService;
    private final String videoM3u8;
    private final static String indexM3U8 = "index.m3u8";
    private final static String tsKey = "ts" + FileUtil.separator + "key";
    private final static String tsIndexM3U8 = "ts" + FileUtil.separator + "index.m3u8";


    @Autowired
    public PlayController(AceEnvironment aceEnvironment, FilesService filesService) {
        this.filesService = filesService;
        this.videoM3u8 = aceEnvironment.getVideoM3u8();

    }

    //method call => accessPlay() => play() => getM3U8() => getKey() => getTs()
    @RequestMapping(value = "/play.html", method = RequestMethod.GET)
    public ModelAndView accessPlay(@RequestParam(value = "playId") String playId, HttpServletRequest request) {
        log.info("access ace/play.html");
        ModelAndView modelAndView;
        log.info("device type: {}", getDevice());
        modelAndView = super.page("ace/tool-pages/play");
        modelAndView.addObject("playId", playId);
        return modelAndView;
    }

    /**
     * 1. access player page
     * 开始加载媒体准备播放
     *
     * @param uuid
     * @param response
     * @param
     */
    @RequestMapping(value = "/play/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void play(@PathVariable String uuid, HttpServletResponse response) {
        log.info("1. access /ace/play/{}=>", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + indexM3U8;
        log.info("Location: {}", location);
        //ModelAndView modelAndView = super.page("ace/tool-pages/play");
        filesService.get(location, response);
        //return modelAndView;
    }


    /**
     * 2. get index.m3u8 info
     * access m3u8
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/play/ts/index.m3u8/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getM3U8(@PathVariable String uuid, HttpServletResponse response) {
        //if (uuid.contains(".ts")) {
        //    getTs(response, uuid,  "4758400b-26c8-49b6-8b08-0df4b621bc52");
        //} else if (uuid.contains("key")) {
        //    getKey("4758400b-26c8-49b6-8b08-0df4b621bc52", response);
        //} else {
        //    log.info("2. access play/ts/index.m3u8/{}", uuid);
        //    String location = videoM3u8 + uuid + FileUtil.separator + tsIndexM3U8;
        //    log.info("Location: {}", location);
        //    filesService.get(location, response);
        //}
        log.info("2. access /ace/play/ts/index.m3u8/{}=>", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + tsIndexM3U8;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }


    /**
     * 3. get ts key
     * access TS key
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/play/ts/index.m3u8/key/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getKey(@PathVariable String uuid, HttpServletResponse response) {
        log.info("3. access /ace/play/ts/index.m3u8/key/{}=>", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + tsKey;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }

    /**
     * 4. get ts media to play
     * 加载TS切片
     *
     * @param response
     * @param ts
     * @param uuid
     */
    @RequestMapping(value = "/play/ts/index.m3u8/{ts}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getTs(HttpServletResponse response, @PathVariable String ts, @PathVariable String uuid) {
        log.info("4. access /ace/play/ts/index.m3u8/{}/{}", ts, uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + "ts" + FileUtil.separator + ts;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }
}

