package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.service.ImagesService;
import com.util.UUID;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

    private final ImagesService imagesService;
    private final String imagesThumbnail;

    public GalleryController(ImagesService imagesService) {
        this.imagesService = imagesService;
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
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping(value = "/image/get/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        log.info("access image/get/{}", fileName);
        imagesService.get(imagesThumbnail, fileName, response);
    }

    /**
     * 图片旋转
     *
     * @param direction
     * @param uuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/thumbnail/rotate/{direction}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public Files rotate(@PathVariable String direction, @PathVariable String uuid) throws Exception {
        log.info("access image/rotate => rotate {} {}", direction, uuid);
        Files f = imagesService.rotateDesc(direction, imagesThumbnail, uuid, UUID.get());
        return f;
    }

}

