package com.ace.restController;

import com.ace.constants.AceEnvironment;
import com.ace.models.entity.Files;
import com.ace.service.FilesService;
import com.ace.utilities.NullUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;


//https://blog.csdn.net/qq_35416214/article/details/106231487
//https://blog.csdn.net/qq_38762237/article/details/81282444?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-1
//https://blog.csdn.net/qq_38762237/article/details/81282444
@RestController
@RequestMapping("/rest/gallery")
@Tag(name = "Gallery")
public class GalleryRestController {
    private static final Logger log = LogManager.getLogger(GalleryRestController.class.getName());

    private static final HashMap<String, String> errorInfo = new HashMap<String, String>();

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

    private final FilesService filesService;
    private final String imagesPath;

    @Autowired
    public GalleryRestController(AceEnvironment aceEnvironment, FilesService filesService) {
        this.filesService = filesService;
        this.imagesPath = aceEnvironment.getImagesPath();
    }


    @GetMapping("/download")
    public String download(String filePath, HttpServletRequest request, HttpServletResponse response) {
        if (NullUtil.nonNull(filePath)) {
            //设置文件路径
            File file = new File(filePath);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + filePath);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {    //注意参数
        Files f = filesService.upload(file, null, imagesPath);
        String result = f.getFileName() + f.getExt();
       // String result = filesService.upload(file, null, imagesPath);
        return result;
    }


    @RequestMapping(value = "/uploads", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public List uploads(@RequestParam(value = "files") MultipartFile[] files) {
        List list = filesService.uploads(files, null, imagesPath);
        return list;
    }





}
