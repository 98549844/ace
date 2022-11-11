package com.constant;

import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Classname: AceEnvironment
 * @Date: 19/7/2021 12:26 下午
 * @Author: garlam
 * @Description:
 */

@Component
@Order(1)
public class AceEnvironment {
    private static Logger log = LogManager.getLogger(AceEnvironment.class.getName());

    //container of application.yml value
    public static Environment environment;
    private static String filePath;
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
        if (OsUtil.getOsName().contains(OsUtil.WINDOWS)) {
            String c = "C:\\";
            String ace = "ACE\\";
            String filePath = c + ace + "files\\";
            String imgPath = c + ace + "images\\";
            String thumbnail = "thumbnail\\";
            String video = c + ace + "videos\\";
            String m3u8 = "m3u8\\";

            //create folder
            FileUtil.mkDirs(filePath);
            FileUtil.mkDirs(imgPath + thumbnail);
            FileUtil.mkDirs(video + m3u8);

            //set value for system use
            setFilePath(filePath);
            setImagesPath(imgPath);
            setImagesThumbnail(imgPath + thumbnail);
            setVideoPath(video);
            setVideoM3u8(video + m3u8);

            String msg = "ACE environment folder setup complete on Windows !!!";
            Console.println(msg, Console.BLUE, Console.BOLD);
        } else if (OsUtil.getOsName().contains(OsUtil.MAC)) {



            String msg = "ACE environment folder setup complete on MAC !!!";
            Console.println(msg, Console.BLUE, Console.BOLD);
        } else {
            String msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
        }
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
}

