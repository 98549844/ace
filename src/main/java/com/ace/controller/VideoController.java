package com.ace.controller;

import com.ace.models.entity.Files;
import com.ace.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * @Classname: VideoController
 * @Date: 26/5/24 PM2:13
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class VideoController {
    private static final Logger log = LogManager.getLogger(VideoController.class.getName());


    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    //直接读取video, 每次读取1M数据返回给客户端,
    //兼容断点续传和手机播放, 增加了ByteArrayOutputStream缓存机制, 减少内存占用
    // https://blog.csdn.net/qq_43319748/article/details/132001036
    @RequestMapping(value = "/video/play/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void play(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("access video/play/{}", fileName);
        Files files = videoService.findByFileName(fileName);
        String location = files.getLocation();
        videoService.play(location, request, response);
    }


    //直接读取video, 每次读取1M数据返回给客户端, 兼容断点续传和手机播放
    /*
    @RequestMapping(value = "/video/play/fileName.html", method = RequestMethod.GET)
    @ResponseBody
    public void play(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //  log.info("access video/get/{}", fileName);
        String location = "/Users/garlam/Downloads/999_1716200879.mp4";


        File file = new File(location);
        if (!file.exists() || !file.getName().endsWith(".mp4")) {
            response.getOutputStream().close();
            return;
        }
        String range = request.getHeader("Range");
        log.info("Range: {}", range);
        if (range != null && range.length() > 7) {
            log.info("该请求符合断点续传");
            range = range.substring(6);
            String[] arr = range.split("-");
            long lenStart = Long.parseLong(arr[0]);
            long end = 0;
            if (arr.length > 1) {
                end = Long.parseLong(arr[1]);
            }
            long contentLength = end > 0 ? (end - (lenStart - 1)) : (file.length() - (lenStart > 0 ? lenStart - 1 : 0));//如果指定范围，就返回范围数据长度，如果没有就返回剩余全部长度
            response.setHeader("Content-Length", String.valueOf(contentLength));
            response.setHeader("Content-Range", "bytes " + lenStart + "-" + (end > 0 ? end : (file.length() - 1)) + "/" + file.length());
            response.setContentType("video/mp4");
            response.setHeader("Accept-Ranges", "bytes");
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());//响应码206表示响应内容为部分数据，需要多次请求
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(lenStart);//设置读取的开始字节数
            if (end > 0) {
                //客户端指定了范围的数据，那就只给范围数据
                int size = (int) (end - lenStart + 1);
                byte[] buffer = new byte[size];
                int len = randomAccessFile.read(buffer);
                if (len != -1) {
                    response.getOutputStream().write(buffer, 0, len);
                }
            } else {
                //没有指定范围
                //视频每次返回一兆数据
                int size = 1048576;//1MB
                byte[] buffer = new byte[size];
                int len;
                while ((len = randomAccessFile.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, len);
                }
            }
            randomAccessFile.close();
        } else {
            log.info("该请求不符合断点续传");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + ".mp4" + "\"");//不能用中文
            response.setHeader("Content-Length", String.valueOf(file.length() - 1));
            response.setHeader("Content-Range", "" + (file.length() - 1));
            response.setHeader("Accept-Ranges", "bytes");
            InputStream inStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
            inStream.close();
        }
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
    */
}
