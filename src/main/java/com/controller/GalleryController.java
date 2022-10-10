package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.models.entity.dao.Files;
import com.service.FilesService;
import com.service.GalleryService;
import com.util.JsonUtil;
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


    /**
     * access to gallery page
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/gallery.html", method = RequestMethod.GET)
    public ModelAndView gallery() throws IOException {
        log.info("access ace/gallery.html");
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        return modelAndView;
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
        log.info("access ace/getImages.html");
        //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        //  String result = JsonUtil.ObjectToFormattedJson(galleryService.getImages());
        //  modelAndView.addObject("ajaxResult", galleryService.getImages());
        //  return modelAndView;
        return galleryService.getImages();
    }


    /**
     * 处理图片显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping("/image/get/{fileName}")
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        log.info("image: /image/get/{}", fileName);
        String name;
        if (fileName.contains(".")) {
            String[] names = fileName.split("\\.");
            name = names[0];
        } else {
            name = fileName;
        }
        filesService.get(name, response);
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
        log.info("access ace/uploads.html => dropzone uuid: {}", uuid);
        //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        List<String> list = filesService.uploads(files, uuid);
        // String result = JsonUtil.ObjectToFormattedJson(list);
        // modelAndView.addObject("ajaxResult", result);
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
        log.info("access ace/remove => delete {}", uuid);
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
        log.info("access ace/delete => delete {}", uuid);
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
        log.info("access ace/download => download {}", uuid);
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
        log.info("access ace/rotate => rotate {} {}", direction, uuid);
      //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        Files f = galleryService.rotate(direction, uuid);
      //  String result = JsonUtil.ObjectToJson(f);
      //  modelAndView.addObject("ajaxResult", result);
        return f;
    }
}

