package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import com.ace.service.FilesService;
import com.ace.service.GalleryService;
import com.util.FileUtil;
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


}

