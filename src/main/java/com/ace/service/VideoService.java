package com.ace.service;

import com.ace.dao.FilesDao;
import com.ace.models.entity.Files;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;


/**
 * @Classname: VideoService
 * @Date: 26/5/24 PM6:41
 * @Author: garlam
 * @Description:
 */


@Service
public class VideoService {
    private static final Logger log = LogManager.getLogger(VideoService.class.getName());

    private final FilesDao filesDao;

    public VideoService(FilesDao filesDao) {
        this.filesDao = filesDao;
    }

    public Files findByFileName(String fileName){
        return filesDao.findFilesByFileName(fileName);
    }

    public void play(String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
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
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //客户端指定了范围的数据，那就只给范围数据
                int size = (int) (end - lenStart + 1);
                byte[] buffer = new byte[size];
                int len = randomAccessFile.read(buffer);
                if (len != -1) {
                    baos.write(buffer, 0, len);
                    //  response.getOutputStream().write(buffer, 0, len);
                }
                byte[] data = baos.toByteArray();
                OutputStream os = response.getOutputStream();
                os.write(data);
                os.flush();
                baos.flush();
                baos.close();
                os.close();
            } else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //没有指定范围
                //视频每次返回一兆数据
                int size = 1048576;//1MB
                byte[] buffer = new byte[size];
                int len;
                while ((len = randomAccessFile.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    // response.getOutputStream().write(buffer, 0, len);
                }
                byte[] data = baos.toByteArray();
                OutputStream os = response.getOutputStream();

                os.write(data);
                os.flush();
                baos.flush();
                baos.close();
                os.close();
            }
            randomAccessFile.close();
        } else {
            log.info("该请求不符合断点续传");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + ".mp4" + "\"");//不能用中文
            response.setHeader("Content-Length", String.valueOf(file.length() - 1));
            response.setHeader("Content-Range", "" + (file.length() - 1));
            response.setHeader("Accept-Ranges", "bytes");
            InputStream inStream = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                //response.getOutputStream().write(buffer, 0, len);
            }
            byte[] data = baos.toByteArray();

            OutputStream os = response.getOutputStream();
            os.write(data);

            os.flush();
            baos.flush();
            baos.close();
            os.close();
            inStream.close();
        }
        response.getOutputStream().flush();
        response.getOutputStream().close();



    }


}

