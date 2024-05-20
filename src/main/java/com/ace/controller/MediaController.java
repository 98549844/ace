package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import com.ace.service.FilesService;
import com.ace.service.MediaService;
import com.ace.util.ResourceUtil;
import com.ace.utilities.FileUtil;
import com.ace.utilities.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    private final FilesService filesService;
    private final MediaService mediaService;
    private final String videoPath;
    private final String videoM3u8;
    private final String thumbnail = "thumbnail.jpg";

    private final ResourceUtil resourceUtil;

    @Autowired
    public MediaController(ResourceUtil resourceUtil, AceEnvironment aceEnvironment, MediaService mediaService, FilesService filesService) {
        this.filesService = filesService;
        this.mediaService = mediaService;
        this.videoPath = aceEnvironment.getVideoPath();
        this.videoM3u8 = aceEnvironment.getVideoM3u8();
        this.resourceUtil = resourceUtil;
    }

    /**
     * access to media
     *
     * @return
     */
    @RequestMapping(value = "/media.html", method = RequestMethod.GET)
    public ModelAndView media() {
        log.info("access ace/media.html");
        return super.page("ace/tool-pages/media");
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

    /**
     * 根据playId切片video
     *
     * @param playId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/media/m3u8StreamProcess.html/{playId}", method = RequestMethod.GET)
    @ResponseBody
    public List m3u8StreamProcess(@PathVariable String playId) throws IOException {
        log.info("access media/m3u8StreamProcess.html playId=>{}", playId);
        log.info("FFmpeg start processing ...");
        List list = mediaService.getM3U8ByPlayId(playId);
        log.info("FFmpeg process complete !!!");
        return list;
    }

    /**
     * 根据playId切片video
     *
     * @return
     * @throws IOException
     */
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
     * 读取所有缩略图资料
     *
     * @return
     */
    @RequestMapping(value = "/media/getThumbnail.html", method = RequestMethod.GET)
    @ResponseBody
    public List getThumbnail() {
        log.info("access media/getThumbnail.html");
        Users user = getCurrentUser();

        List<Files> list = mediaService.findFilesByOwnerOrderByCreatedDate(user.getUserAccount());
        //  List<String> result = new ArrayList<>();
        if (user.getRoleGroup().contains(Users.ADMIN)) {
            //admin可以看到所有缩略图/影片
            return mediaService.findFilesByPathOrderByCreatedDate();
        } else {
            //普通用户只能看到自己的缩略图/影片
            return list;
        }
    }


    /**
     * 默认缩略图显示请求
     * 响应输出图片文件
     *
     * @param
     */
    @RequestMapping(value = "/media/getDefault.html", method = RequestMethod.GET)
    public void getDefault(HttpServletResponse response) throws Exception {
        log.info("access media/getDefault");
        String defaultJPG = "static/assets/images/default.jpg";

        // filesService.getAsStream(defaultJPG, response); //使用流读取图片

        // 生產环境里, 从jar复制到本地tmp文件夹, 再读取tmp文件夹的图片文件
        String location = resourceUtil.getResourcePath(defaultJPG);
        filesService.get(location, response);
    }


    /**
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param uuid
     */
    @RequestMapping(value = "/media/get/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("uuid") String uuid, HttpServletResponse response) throws Exception {
        log.info("access media/get/{}", uuid);
        String name;
        String ext = "jpg";
        if (uuid.contains(".")) {
            name = StringUtil.split(uuid, ".")[0];
            ext = StringUtil.split(uuid, ".")[1];
        } else {
            name = uuid;
        }
        String location = videoM3u8 + name + FileUtil.separator + thumbnail;
        try {
            filesService.get(location, response);
            ImageIO.write(ImageIO.read(new File(location)), ext, response.getOutputStream());
        } catch (IOException e) {
            //如果读缩略图失败, 读取静态文件
            e.printStackTrace();
            location = resourceUtil.getResourcePath("static/assets/images/error.png");
            filesService.get(location, response);
        }
    }

    // thumbnail: function => 只有是图片才会调用呢个js
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
        boolean rs = filesService.delete(uuid); //删除原文件和数据
        boolean deleteDir = filesService.deleteDirectories(videoM3u8 + uuid); //删除切片文件和文件夹
        return rs && deleteDir;
    }
}

