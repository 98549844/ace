package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Folders;
import com.ace.service.FilesService;
import com.ace.service.FoldersService;
import com.ace.utilities.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Classname: FileController
 * @Date: 2022/9/23 下午 05:12
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class FileController extends CommonController {
    private static final Logger log = LogManager.getLogger(FileController.class.getName());

    static String filePath = PathUtil.getSystemPath() + "\\src\\main\\resources\\static\\files\\temp\\";


    private final FilesService filesService;
    private final FoldersService foldersService;
    private final String filesPath;

    @Autowired
    public FileController(AceEnvironment aceEnvironment, FilesService filesService, FoldersService foldersService) {
        this.filesService = filesService;
        this.foldersService = foldersService;
        this.filesPath = aceEnvironment.getFilePath();
    }

    /**
     * access to files page
     *
     * @return
     */
    @RequestMapping(value = "/files.html", method = RequestMethod.GET)
    public ModelAndView getFilesTree() {
        log.info("access files");
        Folders root = foldersService.getRootFolder(getCurrentUser());
        List<Folders> subFolders = foldersService.getFolders(getCurrentUser(), root.getParentId());

        ModelAndView modelAndView = super.page("ace/modules/files/file");
        modelAndView.addObject("root", root);
        modelAndView.addObject("folders", subFolders);
        return modelAndView;
    }


    @RequestMapping(value = "/files/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        Files f = filesService.upload(file, null, filePath);
        String result = f.getFileName() + f.getExt();
        return modelAndView;
    }

    @RequestMapping(value = "/files/uploads.html", method = RequestMethod.POST)
    @ResponseBody
    public List uploads(@RequestParam(value = "files") MultipartFile[] files, MultipartHttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        log.info("access files/uploads.html=> dropzone uuid: {}", uuid);
        List<String> list = filesService.uploads(files, uuid, filesPath);
        return list;
    }


    @RequestMapping(value = "/files/delete", method = RequestMethod.GET)
    public ModelAndView delete() {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.error("success call delete file method");
        return modelAndView;
    }


    //springboot 文件上传示例    https://www.cnblogs.com/Marydon20170307/p/15794176.html
    //AjaxFileUpload   https://blog.csdn.net/qq_43037478/article/details/109810521
    //jQuery FileUpload http://fileupload.cndoc.wiki/doc/30
    //jQuery FileUpload https://blog.csdn.net/bianxiezhe/article/details/124995016
    //使用jquery.fileupload.js上传文件时添加进度条 https://blog.csdn.net/yufeng005/article/details/85788821
    //Spring-boot之jQuery File Upload后台配置方法以及前台跨域  https://www.cnblogs.com/mabylove/p/7397466.html
    //SpringBoot使用jQuery File Upload图片上传服务器回显相关 https://blog.csdn.net/wo88798/article/details/80511195

    //文件上传思路 https://blog.csdn.net/m0_47396944/article/details/120331765

    //dropzone https://codepen.io/blackjacques/pen/jyxNqL
    //dropzone http://wxb.github.io/dropzonejs.com.zh-CN/dropzonezh-CN/#installation
    //dropzone https://www.dropzone.dev/
    //dropzone https://github.com/dropzone/dropzone
    //File Upload & Image Preview https://codepen.io/uixmat/pen/yadZXv


}

