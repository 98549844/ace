package com;


import com.config.TranscodeConfig;
import com.util.FFmpegUtil;
import com.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * @Classname: Ace
 * @Date: 2022/8/9 上午 09:41
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());
    private final Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
    private final String videoM3u8 = "C:\\ACE\\temp\\m3u8\\";
    private final String videoPath = "C:\\ACE\\temp\\";

    private static MultipartFile getMultipartFile(File file) {
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), file.getName(), "application/octet-stream", fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

    public static TranscodeConfig setupTranscodeConfig() {
        TranscodeConfig t = new TranscodeConfig();
        t.setPoster("00:00:00.001");
        t.setTsSeconds("15");
        t.setCutStart("");
        t.setCutEnd("");
        return t;
    }

    public static void main(String[] args) throws IOException {
        String location = "C:\\ACE\\";
        Ace ace = new Ace();
        File video = new File("C:\\tmp\\002.mp4");
        ace.uploads(getMultipartFile(video), setupTranscodeConfig());
    }


    public Object uploads(MultipartFile video, TranscodeConfig transcodeConfig) throws IOException {
        Long size = video.getSize();
        log.info("文件信息: title= {}, size= {}mb", video.getOriginalFilename(), size / 1048576);
        log.info("转码配置: {}", transcodeConfig.toString());
        // 原始文件名称，也就是视频的标题
        String title = video.getOriginalFilename();
        String suffix = title.substring(title.lastIndexOf("."));

        // io到临时文件
        Path tempFile = tempDir.resolve(title);
        log.info("io到临时文件: {}", tempFile.toString());
        try {
            log.info("Generate by UUID.randomUUID()!!!");
            String uuid = UUID.get();
            video.transferTo(tempFile);
            // 删除后缀
            //title = title.substring(0, title.lastIndexOf("."));
            // 创建视频目录
            Path targetFolder = Files.createDirectories(Paths.get(videoM3u8, uuid));
            log.info("创建文件夹目录：{}", targetFolder);
            Files.createDirectories(targetFolder);
            // 执行转码操作
            log.info("开始转码");
            try {
                FFmpegUtil.transcodeToM3u8(tempFile.toString(), targetFolder.toString(), transcodeConfig);
            } catch (Exception e) {
                log.error("转码异常：{}", e.getMessage());
                Map<String, Object> result = new HashMap<>();
                result.put("success", false);
                result.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
            // 封装结果
            Map<String, Object> videoInfo = new HashMap<>();
            videoInfo.put("title", uuid);
            videoInfo.put("m3u8", String.join("/", "", uuid, "index.m3u8"));
            videoInfo.put("poster", String.join("/", "", uuid, "poster.jpg"));

            // 文件存储全路径
            File targetFile = new File(videoPath + uuid + suffix);
            video.transferTo(targetFile);

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", videoInfo);

            return result;
        } finally {
            // 始终删除临时文件
            Files.delete(tempFile);
        }
    }

}



