package com.controller;

import com.config.TranscodeConfig;
import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FilesService;
import com.service.MediaService;
import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname: MediaController
 * @Date: 2022/10/9 下午 10:17
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MediaController extends CommonController {
    private static final Logger log = LogManager.getLogger(MediaController.class.getName());

    private FilesService filesService;
    private MediaService mediaService;
    private String videoPath;
    private String videoM3u8;
    private final static String indexM3U8 = "index.m3u8";
    private final static String tsIndexM3U8 = "ts" + FileUtil.separator + "index.m3u8";
    private final static String tsKey = "ts" + FileUtil.separator + "key";


    @Autowired
    public MediaController(MediaService mediaService, FilesService filesService) {
        this.filesService = filesService;
        this.mediaService = mediaService;
        this.videoPath = AceEnvironment.getVideoPath();
        this.videoM3u8 = AceEnvironment.getVideoM3u8();
    }

    /** access to media
     * @param request
     * @return
     */
    @RequestMapping(value = "/media.html", method = RequestMethod.GET)
    public ModelAndView media(HttpServletRequest request) {
        log.info("access ace/media.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/media");
        return modelAndView;
    }


    /** 开始加载媒体准备播放
     * @param uuid
     * @param response
     * @param request
     */
    @RequestMapping(value = "/media/play/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void play(@PathVariable String uuid, HttpServletResponse response, HttpServletRequest request) {
        log.info("access ace/play.html uuid: {}", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + indexM3U8;
        log.info("Location: {}", location);
        filesService.get(location, null, response);
    }

    /** access m3u8
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getM3U8(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access media/play/ts/index.m3u8/{}", uuid);
        //String uuid = request.getParameter("uuid");
        //log.error("uuid:  " + uuid);
        // String location = "C:\\ACE\\videos\\m3u8\\0011\\ts\\index.m3u8";
        String location = videoM3u8 + uuid + FileUtil.separator + tsIndexM3U8;
        log.info("Location: {}", location);
        filesService.get(location, null, response);
    }

    /** access TS key
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/key/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getKey(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access media/play/ts/index.m3u8/key/{}", uuid);
        // String location = "C:\\ACE\\videos\\m3u8\\0011\\ts\\key";
        String location = videoM3u8 + uuid + FileUtil.separator + tsKey;
        log.info("Location: {}", location);
        filesService.get(location, null, response);
    }

    /** 加载TS切片
     * @param response
     * @param ts
     * @param uuid
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/{ts}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getTs(HttpServletResponse response, @PathVariable String ts, @PathVariable String uuid) {
        log.info("access media/play/ts//index.m3u8/{}/{}", ts, uuid);
        // String location = "C:\\ACE\\videos\\m3u8\\0011\\ts\\000000.ts";
        String location = videoM3u8 + uuid + FileUtil.separator + "ts" + FileUtil.separator + ts;
        log.info("Location: {}", location);
        filesService.get(location, null, response);
    }

    /**
     * 上传视频进行切片处理，返回访问路径
     *
     * @param video
     * @param transcodeConfig
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/media/upload.html")
    @ResponseBody
    public Object uploads(@RequestPart(name = "file", required = true) MultipartFile video, @RequestPart(name = "config", required = true) TranscodeConfig transcodeConfig) throws IOException {
        return mediaService.uploads(video, transcodeConfig);
    }


}

