package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.models.entity.Files;
import com.service.FilesService;
import com.service.GalleryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Classname: GalleryController
 * @Date: 7/7/2021 2:44 下午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class GalleryController extends CommonController {
    private static final Logger log = LogManager.getLogger(GalleryController.class.getName());

    private final GalleryService galleryService;
    private final FilesService filesService;
    private final String imagePath;
    private final String imagesThumbnail;

    @Autowired
    public GalleryController(GalleryService galleryService, FilesService filesService) {
        this.galleryService = galleryService;
        this.filesService = filesService;
        this.imagePath = AceEnvironment.getImagesPath();
        this.imagesThumbnail = AceEnvironment.getImagesThumbnail();
    }


    /**
     * access to gallery page
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/gallery.html", method = RequestMethod.GET)
    public ModelAndView gallery() throws IOException {
        log.info("access gallery.html");
        return super.page("ace/tool-pages/gallery");
    }

    /**
     * get all images
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getImages.html", method = RequestMethod.GET)
    @ResponseBody
    public List getImages() throws IOException {
        log.info("access getImages.html");
        List ls = galleryService.getImages(getCurrentUser());
        return ls;
    }

    /**
     * get images limitation
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getImagesByLimit.html/{paging}", method = RequestMethod.GET)
    @ResponseBody
    public List getImagesByLimit(@PathVariable(value = "paging") int paging) throws IOException {
        log.info("access getImagesByLimit.html paging: {}",paging);

        List ls = galleryService.getImagesByLimit(getCurrentUser(), paging);
        return ls;
    }


    /**
     * 暂时弃用
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping(value = "/image/thumbnail/get/{fileName}", method = RequestMethod.GET)
    public void getThumbnail(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        log.info("access image/thumbnail/get/{}", fileName);
        String thumbnail = imagesThumbnail + fileName + filesService.findFilesByFileName(fileName).getExt();
        filesService.get(thumbnail, response);
    }

    /**
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping(value = "/image/get/{fileName}", method = RequestMethod.GET)
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        log.info("access image/get/{}", fileName);
        String name;
        if (!fileName.contains(".")) {
            Files f = filesService.findFilesByFileName(fileName);
            String ext = f.getExt();
            name = fileName + ext;
        } else {
            name = fileName;
        }
        filesService.get(imagesThumbnail + name, response);
    }


    /**
     * 图片上传
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/image/uploads.html", method = RequestMethod.POST)
    @ResponseBody
    public List<String> uploads(@RequestParam(value = "files") MultipartFile[] files, MultipartHttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        log.info("access image/uploads.html => dropzone uuid: {}", uuid);
        List<String> list = filesService.uploads(files, uuid, imagePath);
        return list;
    }


    /**
     * 移除图片
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/image/remove/{uuid}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable String uuid) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access image/remove => delete {}", uuid);
        modelAndView.addObject("delete", filesService.delete(uuid));
        return modelAndView;
    }

    /**
     * 图片删除
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/image/delete/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@PathVariable String uuid) {
        //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        log.info("access image/delete => delete {}", uuid);
        boolean rs = filesService.delete(uuid);
        // String result = JsonUtil.ObjectToJson(rs);
        //  modelAndView.addObject("ajaxResult", result);
        return rs;

    }

    /**
     * 下载图片
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/image/download/{uuid}", method = RequestMethod.GET)
    public void download(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access image/download => download {}", uuid);
        boolean result = filesService.download(uuid, response);
        if (result) {
            log.info("download {} {}", uuid, "success");
        } else {
            log.error("download {} {}", uuid, "fail");
        }
    }

    /**
     * 图片旋转
     *
     * @param direction
     * @param uuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/image/rotate/{direction}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public Files rotate(@PathVariable String direction, @PathVariable String uuid) throws Exception {
        log.info("access image/rotate => rotate {} {}", direction, uuid);
        //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        Files f = galleryService.rotate(direction, uuid);
        //  String result = JsonUtil.ObjectToJson(f);
        //  modelAndView.addObject("ajaxResult", result);
        return f;
    }
}

