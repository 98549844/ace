package com.ace.constant;

import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: AceEnvironment
 * @Date: 19/7/2021 12:26 下午
 * @Author: garlam
 * @Description:
 */

@Component
@Order(1)
public class AceEnvironment {
    private static final Logger log = LogManager.getLogger(AceEnvironment.class.getName());

    //container of application.yml value
    public static Environment environment;
    private static String filePath;
    private static String misc;
    //  private static String filesTemp;
    private static String imagesPath;
    private static String imagesThumbnail;
    private static String videoPath;
    private static String videoM3u8;


    @Autowired
    public AceEnvironment(Environment environment) {
        AceEnvironment.environment = environment;
        setUp();
    }


    public static void setUp() {
        folderSetUp();
    }


    private static void folderSetUp() {
        String msg;
        String osName = OsUtil.getOsName();

        String root;
        String ace;
        String filePath;
        String imgPath;
        String thumbnail;
        String video;
        String m3u8;
        String miscellaneous;
        Map<String, String> map = new HashMap<>();


        if (osName.contains(OsUtil.WINDOWS)) {
            root = "C:\\";
            ace = "ace\\";
            filePath = root + ace + "files\\";
            imgPath = root + ace + "images\\";
            thumbnail = imgPath + "thumbnail\\";
            video = root + ace + "videos\\";
            m3u8 = video + "m3u8\\";
            miscellaneous = root + ace + "misc\\";

            map.put("root", root);
            map.put("ace", ace);
            map.put("filePath", filePath);
            map.put("imgPath", imgPath);
            map.put("thumbnail", thumbnail);
            map.put("video", video);
            map.put("m3u8", m3u8);
            map.put("miscellaneous", miscellaneous);

            createFolderAndSetValue(map);
            msg = "ACE environment setup complete : Windows !!!";
        } else if (osName.contains(OsUtil.MAC)) {
            root = "/Users/garlam/";
            ace = "ace/";
            filePath = root + ace + "files/";
            imgPath = root + ace + "images/";
            thumbnail = imgPath + "thumbnail/";
            video = root + ace + "videos/";
            m3u8 = video + "m3u8/";
            miscellaneous = root + ace + "misc/";

            map.put("root", root);
            map.put("ace", ace);
            map.put("filePath", filePath);
            map.put("imgPath", imgPath);
            map.put("thumbnail", thumbnail);
            map.put("video", video);
            map.put("m3u8", m3u8);
            map.put("miscellaneous", miscellaneous);

            createFolderAndSetValue(map);
            msg = "ACE environment setup complete : MAC !!!";
        } else if (osName.contains(OsUtil.LINUX)) {
            root = "/opt/workspace/";
            ace = "ace/";
            filePath = root + ace + "files/";
            imgPath = root + ace + "images/";
            thumbnail = imgPath + "thumbnail/";
            video = root + ace + "videos/";
            m3u8 = video + "m3u8/";
            miscellaneous = root + ace + "misc/";

            map.put("root", root);
            map.put("ace", ace);
            map.put("filePath", filePath);
            map.put("imgPath", imgPath);
            map.put("thumbnail", thumbnail);
            map.put("video", video);
            map.put("m3u8", m3u8);
            map.put("miscellaneous", miscellaneous);

            createFolderAndSetValue(map);
            msg = "ACE environment setup complete : LINUX !!!";
        } else {
            msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
            return;
        }
        Console.println(msg, Console.BLUE, Console.BOLD);
    }

    private static void createFolderAndSetValue(Map map) {
        //create folder
        FileUtil.mkDirs((String) map.get("filePath"));
        FileUtil.mkDirs((String) map.get("thumbnail"));
        FileUtil.mkDirs((String) map.get("m3u8"));
        FileUtil.mkDirs((String) map.get("miscellaneous"));

        //set value for system use
        setFilePath((String) map.get("filePath"));
        setImagesPath((String) map.get("imgPath"));
        setImagesThumbnail((String) map.get("thumbnail"));
        setVideoPath((String) map.get("video"));
        setVideoM3u8((String) map.get("m3u8"));
        setMisc((String) map.get("miscellaneous"));
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        AceEnvironment.filePath = filePath;
    }

    public static String getImagesPath() {
        return imagesPath;
    }

    public static void setImagesPath(String imagesPath) {
        AceEnvironment.imagesPath = imagesPath;
    }

    public static String getImagesThumbnail() {
        return imagesThumbnail;
    }

    public static void setImagesThumbnail(String imagesThumbnail) {
        AceEnvironment.imagesThumbnail = imagesThumbnail;
    }

    public static String getVideoM3u8() {
        return videoM3u8;
    }

    public static void setVideoM3u8(String videoM3u8) {
        AceEnvironment.videoM3u8 = videoM3u8;
    }

    public static String getVideoPath() {
        return videoPath;
    }

    public static void setVideoPath(String videoPath) {
        AceEnvironment.videoPath = videoPath;
    }

    public static String getMisc() {
        return misc;
    }

    public static void setMisc(String misc) {
        AceEnvironment.misc = misc;
    }
}

