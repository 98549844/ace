package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FilesService;
import com.service.MediaService;
import com.util.FileUtil;
import com.util.JsonUtil;
import com.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    private final String thumbnail = "thumbnail.jpg";
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


    /**
     * access to media
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/media.html", method = RequestMethod.GET)
    public ModelAndView media(HttpServletRequest request) {
        log.info("access ace/media.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/media");
        return modelAndView;
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

    /**
     * access m3u8
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getM3U8(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access media/play/ts/index.m3u8/{}", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + tsIndexM3U8;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }

    /**
     * access TS key
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/key/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getKey(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access media/play/ts/index.m3u8/key/{}", uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + tsKey;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }

    /**
     * 加载TS切片
     *
     * @param response
     * @param ts
     * @param uuid
     */
    @RequestMapping(value = "/media/play/ts/index.m3u8/{ts}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void getTs(HttpServletResponse response, @PathVariable String ts, @PathVariable String uuid) {
        log.info("access media/play/ts//index.m3u8/{}/{}", ts, uuid);
        String location = videoM3u8 + uuid + FileUtil.separator + "ts" + FileUtil.separator + ts;
        log.info("Location: {}", location);
        filesService.get(location, response);
    }

    /**
     * 上传视频进行切片处理，返回访问路径
     *
     * @param media
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/media/uploads.html")
    @ResponseBody
    //  public Object uploads(@RequestPart(name = "media") MultipartFile media, @RequestPart(name = "config") TranscodeConfig transcodeConfig) throws IOException {
    public List<String> uploads(@RequestPart(name = "media") MultipartFile[] media, MultipartHttpServletRequest request) throws IOException {
        String uuid = request.getParameter("uuid");
        log.info("access media/uploads.html uuid: {}", uuid);
        List<String> list = filesService.uploads(media, uuid, videoPath);
        return list;
    }


    @RequestMapping(value = "/media/m3u8StreamProcess.html", method = RequestMethod.GET)
    @ResponseBody
    public List m3u8StreamProcess() throws IOException {
        log.info("access media/m3u8StreamProcess.html");
        log.info("FFmpeg start processing ...");
        List list = mediaService.getM3U8();
        log.info("FFmpeg process complete !!!");
        return list;
    }

    /**
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param uuid
     */
    @RequestMapping(value = "/media/get/{uuid}", method = RequestMethod.GET)
    public void get(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        log.info("access media/get/{}", uuid);
        String name;
        if (uuid.contains(".")) {
            name = StringUtil.split(uuid, ".")[0];
        } else {
            name = uuid;
        }
        String location = videoM3u8 + name + FileUtil.separator + thumbnail;
        filesService.get(location, response);
    }

    // thumbnail: function => 好似只有是图片才会调用呢个js
    @RequestMapping(value = "/media/get/videoIcon.png", method = RequestMethod.GET)
    public void getVideoIcon(HttpServletResponse response) {
        log.info("access media/getVideoIcon");
        String videoIcon = "src/main/resources/static/assets/images/video/video1.png";
        filesService.get(videoIcon, response);
    }

    /**
     * 影片删除
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/media/delete/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@PathVariable String uuid) {
        log.info("access media/delete => delete {}", uuid);
        boolean rs = filesService.delete(uuid);
        return rs;
    }
}

