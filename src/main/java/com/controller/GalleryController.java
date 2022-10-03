package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FilesService;
import com.service.GalleryService;
import com.util.JsonUtil;
import com.util.MapUtil;
import com.util.TypeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.loader.custom.ResultRowProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private static Logger log = LogManager.getLogger(GalleryController.class.getName());

    private GalleryService galleryService;
    private FilesService filesService;
    private final String imagePath;
    private final String imagePathTemp;

    @Autowired
    public GalleryController(GalleryService galleryService, FilesService filesService) {
        this.galleryService = galleryService;
        this.filesService = filesService;
        this.imagePath = AceEnvironment.getImagesPath();
        this.imagePathTemp = AceEnvironment.getImagesTemp();
    }


    @RequestMapping(value = "/gallery.html", method = RequestMethod.GET)
    public ModelAndView gallery() throws IOException {
        log.info("access ace/gallery.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        return modelAndView;
    }

    @RequestMapping(value = "/getImages.html", method = RequestMethod.GET)
    public ModelAndView getImages() throws IOException {
        log.info("access ace/getImages.html");
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        String result = JsonUtil.ObjectToFormattedJson(galleryService.getImages());
        modelAndView.addObject("ajaxResult", result);
        return modelAndView;
    }


    /**
     * 处理图片显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping("/image/get/{fileName}")
    public void responseImage(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        log.info("image: /image/get/{}", fileName);
        filesService.get(imagePathTemp + fileName, response);
    }


    @RequestMapping(value = "/image/uploads.html", method = RequestMethod.POST)
    public ModelAndView uploads(@RequestParam(value = "files") MultipartFile[] files, MultipartHttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        log.info("access ace/uploads.html => dropzone uuid: {}", uuid);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        List<String> list = filesService.uploads(files, uuid);
        String result = JsonUtil.ObjectToFormattedJson(list);
        modelAndView.addObject("ajaxResult", result);
        return modelAndView;
    }


    @RequestMapping(value = "/image/remove/{uuid}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable String uuid) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access ace/delete => delete {}", uuid);
        modelAndView.addObject("delete", filesService.delete(uuid));
        return modelAndView;
    }

    @RequestMapping(value = "/image/delete/{uuid}", method = RequestMethod.GET)
    public void delete(@PathVariable String uuid) {
        // 有问题,数据查不出
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access ace/delete => delete {}", uuid);
    //    filesService.delete(uuid);
    }

    @RequestMapping(value = "/image/download/{uuid}", method = RequestMethod.GET)
    public void download(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access ace/download => download {}", uuid);
        boolean result = filesService.download(uuid, response);
        if (result) {
            log.info("download {} {}", uuid, "success");
        } else {
            log.error("download {} {}", uuid, "fail");
        }
    }

}

