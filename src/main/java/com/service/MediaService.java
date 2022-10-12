package com.service;

import com.config.TranscodeConfig;
import com.constant.AceEnvironment;
import com.util.FFmpegUtil;
import com.util.NullUtil;
import com.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: MediaService
 * @Date: 2022/10/11 下午 04:40
 * @Author: kalam_au
 * @Description:
 */

@Service
public class MediaService {
    private static final Logger log = LogManager.getLogger(MediaService.class.getName());

    private String videoM3u8;
    private String videoPath;
    private FilesService filesService;

    @Autowired
    public MediaService(FilesService filesService) {
        this.filesService = filesService;
        this.videoM3u8 = AceEnvironment.getVideoM3u8();
        this.videoPath = AceEnvironment.getVideoPath();
    }


    //  @Value("${app.video-folder}")
    //  private String videoFolder;
    private final Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));

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


            com.models.entity.dao.Files f = new com.models.entity.dao.Files();
            f.setPath(videoPath);
            f.setOriginationName(title);
            f.setExt(suffix);
            f.setLocation(videoPath + uuid + suffix);
            f.setRemark("ACE Application UUID: " + uuid);
            f.setSize(size / 1024);
            f.setFileName(uuid);

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("data", videoInfo);
            filesService.save(f);

            return result;
        } finally {
            // 始终删除临时文件
            Files.delete(tempFile);
        }
    }

}

