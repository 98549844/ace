package com.constant;

import com.models.entity.dao.Files;
import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    private static String imagesPath;
    private static String filesTemp;
    private static String imagesTemp;
    private static String videoTemp;
    private static String videoPath;


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
            String fsPath = "C:\\ACE\\files\\";
            String imgPath = "C:\\ACE\\images\\";
            String vPath = "C:\\ACE\\videos\\";
            String temp = "temp\\";
            setFolders(temp, fsPath, imgPath, vPath);

            String msg = "ACE environment folder setup complete on Windows !!!";
            Console.println(msg, Console.BLUE, Console.BOLD);
        } else if (OsUtil.getOsName().contains(OsUtil.MAC)) {
            String fsPath = "TODO";
            String imgPath = "TODO";
            String vPath = "TODO";
            String temp = "temp/";

            setFolders(temp, fsPath, imgPath, vPath);

            String msg = "ACE environment folder setup complete on MAC !!!";
            Console.println(msg, Console.BLUE, Console.BOLD);
        } else {
            String msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
        }
    }

    private static void setFolders(String temp, String folder, String images, String video) {
        FileUtil.mkDirs(folder + temp);
        FileUtil.mkDirs(images + temp);
        FileUtil.mkDirs(video + temp);

        setFilePath(folder);
        setFilesTemp(folder + temp);
        setImagesPath(images);
        setImagesTemp(images + temp);
        setVideoPath(video);
        setVideoTemp(video + temp);
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

    public static String getFilesTemp() {
        return filesTemp;
    }

    public static void setFilesTemp(String filesTemp) {
        AceEnvironment.filesTemp = filesTemp;
    }

    public static String getImagesTemp() {
        return imagesTemp;
    }

    public static void setImagesTemp(String imagesTemp) {
        AceEnvironment.imagesTemp = imagesTemp;
    }

    public static String getVideoTemp() {
        return videoTemp;
    }

    public static void setVideoTemp(String videoTemp) {
        AceEnvironment.videoTemp = videoTemp;
    }

    public static String getVideoPath() {
        return videoPath;
    }

    public static void setVideoPath(String videoPath) {
        AceEnvironment.videoPath = videoPath;
    }
}

