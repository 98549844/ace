package com.ace.restController;

import com.ace.constant.AceEnvironment;
import com.ace.service.FilesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @Classname: ResumeRestController
 * @Date: 18/5/24 AM2:36
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/resume")
@Tag(name = "Resume")
public class ResumeRestController {
    private static final Logger log = LogManager.getLogger(ResumeRestController.class.getName());

    private final FilesService filesService;
    private final AceEnvironment aceEnvironment;


    public ResumeRestController(FilesService filesService, AceEnvironment aceEnvironment) {
        this.filesService = filesService;
        this.aceEnvironment = aceEnvironment;
    }

    /**
     * 文件下載请求
     * 响应输出文件
     */
    @RequestMapping(value = "/get.html", method = RequestMethod.GET)
    public void get(HttpServletResponse response) throws IOException {
        log.info("access resume/get.html");
        String location = aceEnvironment.getAce() + "garlam-resume.docx";
        filesService.download(location, response);
    }
}

