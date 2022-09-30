package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FilesService;
import com.service.GalleryService;
import com.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    public GalleryController(GalleryService galleryService, FilesService filesService) {
        this.galleryService = galleryService;
        this.filesService = filesService;
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
        log.info("output image: ace/showImage/{}", fileName);
        filesService.get(AceEnvironment.getImagesTemp() + fileName, response);
    }


    @RequestMapping(value = "/gallery/uploads.html", method = RequestMethod.POST)
    public ModelAndView uploads(@RequestParam(value = "files") MultipartFile[] files) {
        log.info("access ace/uploads.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        List<String> list = filesService.uploads(files);
        return modelAndView;
    }


    @RequestMapping(value = "/gallery/delete/{fileName}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable String fileName) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access ace/delete => delete {}", fileName);
        modelAndView.addObject("delete", filesService.delete(fileName));
        return modelAndView;
    }


}

