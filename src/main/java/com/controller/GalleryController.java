package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.FileService;
import com.service.GalleryService;
import com.util.FileUtil;
import com.util.JsonUtil;
import com.util.PathUtil;
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
    private FileService fileService;

    @Autowired
    public GalleryController(GalleryService galleryService, FileService fileService) {
        this.galleryService = galleryService;
        this.fileService = fileService;
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
    @RequestMapping("/showImage/{fileName}")
    public void showPicture(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        log.info("output image: ace/showImage/{}",fileName);
        File imgFile = new File(AceEnvironment.getImagesTemp() + fileName);
        try {
            InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024]; // 图片文件流缓存池
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    @RequestMapping(value = "/gallery/uploads.html", method = RequestMethod.POST)
    public ModelAndView uploads(@RequestParam(value = "files") MultipartFile[] files) {
        log.info("access ace/uploads.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        String imagesPath = AceEnvironment.getImagesPath();
        List<String> list = fileService.uploads(imagesPath, files);
        return modelAndView;
    }


    @RequestMapping(value = "/gallery/delete.html", method = RequestMethod.GET)
    public ModelAndView delete() {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access ace/delete.html");



        return modelAndView;
    }


}

