package com.restController;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;


//https://blog.csdn.net/qq_35416214/article/details/106231487
//https://blog.csdn.net/qq_38762237/article/details/81282444?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
//https://blog.csdn.net/qq_38762237/article/details/81282444
@RestController
@RequestMapping("/rest/gallery")
@Api(tags = "gallery")
public class GalleryRestController {
    private static Logger log = LogManager.getLogger(ApiRestController.class.getName());

    private static HashMap<String, String> errorInfo = new HashMap<String, String>();

    static {
        HashMap<String, String> tmp = errorInfo;
        // 默认成功
        tmp.put("SUCCESS", "SUCCESS");
        tmp.put("NOFILE", "msg.not_include_file");
        tmp.put("TYPE", "msg.file_not_allow");
        tmp.put("SIZE", "msg.file_size_limit");
        tmp.put("REQUEST", "msg.upload_request_error");
        tmp.put("IO", "msg.io_error");
        tmp.put("DIR", "msg.create_directory_error");
        tmp.put("UNKNOWN", "msg.unknown_error");
    }

    static String filePath = "src/main/resources/file/image"; // 上传后的路径
    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            log.info("File is empty !");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名

        File t = new File(filePath);
        filePath = t.getAbsolutePath() + "/";

        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            log.info("transfer file to : {}", filePath);
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return "file";
    }

}
