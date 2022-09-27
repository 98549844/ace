package com.controller;

import com.controller.common.CommonController;
import com.service.FileService;
import com.service.GalleryService;
import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    private static Logger log = LogManager.getLogger(GalleryController.class.getName());

    static String filePath = PathUtil.getSystemPath() + "\\src\\main\\resources\\static\\files\\images\\";
    private GalleryService galleryService;
    private FileService fileService;

    @Autowired
    public GalleryController(GalleryService galleryService, FileService fileService) {
        this.galleryService = galleryService;
        this.fileService = fileService;
    }


    @RequestMapping(value = "/gallery.html", method = RequestMethod.GET)
    public ModelAndView gallery() throws IOException {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        modelAndView.addObject("images", galleryService.getImages());
        return modelAndView;
    }

    @RequestMapping(value = "/gallery/uploads.html", method = RequestMethod.POST)
    public ModelAndView uploads(@RequestParam(value = "files") MultipartFile[] files) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        List<String> list = fileService.uploads(filePath, files);
        return modelAndView;
    }



    @RequestMapping(value = "/gallery/delete.html", method = RequestMethod.GET)
    public ModelAndView delete() {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");

        log.error("success call delete file method");

        return modelAndView;
    }


}

