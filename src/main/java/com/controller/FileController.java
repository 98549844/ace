package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public static void main(String[] args) {
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

}
