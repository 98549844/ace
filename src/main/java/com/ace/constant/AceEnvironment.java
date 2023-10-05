package com.ace.constant;

import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;

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

    private static String separator;
    private static String root;
    private static String ace;
    private static String users;
    private static String filePath;
    private static String misc;
    private static String tmp;
    private static String imagesPath;
    private static String imagesThumbnail;
    private static String videoPath;
    private static String videoM3u8;

    public static void main(String[] args) {
        AceEnvironment.setUp();
        System.out.println(getAce());
        System.out.println(getTmp());
    }

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
        String separator = File.separator;

        if (osName.contains(OsUtil.WINDOWS)) {
            //separator = "\\";
            root = "C:" + separator;
            createFolderAndSetValue(root, separator);
            msg = "ACE environment setup complete : Windows !!!";
        } else if (osName.contains(OsUtil.MAC)) {
            //separator = "/";
            root = "/Users/garlam" + separator;
            createFolderAndSetValue(root, separator);
            msg = "ACE environment setup complete : MAC !!!";
        } else if (osName.contains(OsUtil.LINUX)) {
            //separator = "/";
            root = "/opt/workspace" + separator;
            createFolderAndSetValue(root, separator);
            msg = "ACE environment setup complete : LINUX !!!";
        } else {
            msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
            return;
        }
        Console.println(msg, Console.BLUE, Console.BOLD);
    }

    private static void createFolderAndSetValue(String root, String separator) {
        String ace = root + "ace" + separator;

        String users = ace + "users" + separator;

        String filePath = ace + "files" + separator;

        String imgPath = ace + "images" + separator;
        String thumbnail = imgPath + "thumbnail" + separator;

        String video = ace + "videos" + separator;
        String m3u8 = video + "m3u8" + separator;

        String miscellaneous = ace + "misc" + separator;
        String tamp = ace + "tmp" + separator;

        //create folder
        FileUtil.mkDirs(filePath); //已包含root
        FileUtil.mkDirs(users); //已包含root
        FileUtil.mkDirs(thumbnail); //已包括images
        FileUtil.mkDirs(m3u8); //已包括video
        FileUtil.mkDirs(miscellaneous);
        FileUtil.mkDirs(tamp);

        //set value for system use
        setSeparator(separator);
        setRoot(root);
        setAce(ace);
        setUsers(users);
        setFilePath(filePath);
        setImagesPath(imgPath);
        setImagesThumbnail(thumbnail);
        setVideoPath(video);
        setVideoM3u8(m3u8);
        setMisc(miscellaneous);
        setTmp(tamp);
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

    public static String getSeparator() {
        return separator;
    }

    public static void setSeparator(String separator) {
        AceEnvironment.separator = separator;
    }

    public static String getRoot() {
        return root;
    }

    public static void setRoot(String root) {
        AceEnvironment.root = root;
    }

    public static String getUsers() {
        return users;
    }

    public static void setUsers(String users) {
        AceEnvironment.users = users;
    }

    public static String getAce() {
        return ace;
    }

    public static void setAce(String ace) {
        AceEnvironment.ace = ace;
    }

    public static String getTmp() {
        return tmp;
    }

    public static void setTmp(String tmp) {
        AceEnvironment.tmp = tmp;
    }
}

