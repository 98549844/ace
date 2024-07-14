package com.ace.constants;

import com.ace.utilities.Console;
import com.ace.utilities.FileUtil;
import com.ace.utilities.OsUtil;
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
    public Environment environment;

    private String separator;
    private String root;
    private String ace;
    private String users;
    private String filePath;
    private String misc;
    private String tmp;
    private String imagesPath;
    private String imagesThumbnail;
    private String videoPath;
    private String videoM3u8;
    private String systemTmpDir;
    private String systemDir;
    private String newLine;


//    public static void main(String[] args) {
//        AceEnvironment.setUp();
//        System.out.println(getAce());
//        System.out.println(getTmp());
//    }

    public AceEnvironment(Environment environment) {
        this.environment = environment;
        setUp();
    }


    public void setUp() {
        folderSetUp();
    }


    private void folderSetUp() {
        String msg;
        String osName = OsUtil.getOsName();

        String root;
        String separator = File.separator;

        if (osName.contains(OsUtil.WINDOWS)) {
            root = "C:" + separator;
            msg = "ACE environment setup complete: Windows";
        } else if (osName.contains(OsUtil.MAC)) {
            root = "/Users/garlam" + separator;
            msg = "ACE environment setup complete: MAC";
        } else if (osName.contains(OsUtil.LINUX)) {
            root = "/opt/workspace" + separator;
            msg = "ACE environment setup complete: LINUX";
        } else {
            msg = "WARNING => UNKNOWN OS, ACE Environment setup incomplete !!!";
            Console.println(msg, Console.RED, Console.BOLD);
            return;
        }
        createFolderAndSetValue(root, separator);
        Console.println(msg, Console.BLUE, Console.BOLD);
    }

    private void createFolderAndSetValue(String root, String separator) {
        String ace = root + "ace" + separator;

        String users = ace + "users" + separator;

        String filePath = ace + "files" + separator;

        String imgPath = ace + "images" + separator;
        String thumbnail = imgPath + "thumbnail" + separator;

        String video = ace + "videos" + separator;
        String m3u8 = video + "m3u8" + separator;

        String miscellaneous = ace + "misc" + separator;
        String tmp = ace + "tmp" + separator;


        //create folder
        FileUtil.mkDirs(filePath); //已包含root
        FileUtil.mkDirs(users); //已包含root
        FileUtil.mkDirs(thumbnail); //已包括images
        FileUtil.mkDirs(m3u8); //已包括video
        FileUtil.mkDirs(miscellaneous);
        FileUtil.mkDirs(tmp);

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
        setTmp(tmp);
        setSystemDir(System.getProperty("user.dir"));
        setSystemTmpDir(System.getProperty("java.io.tmpdir"));
        setNewLine(System.lineSeparator());
    }


    public String getSeparator() {
        return separator;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getAce() {
        return ace;
    }

    public void setAce(String ace) {
        this.ace = ace;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getImagesThumbnail() {
        return imagesThumbnail;
    }

    public void setImagesThumbnail(String imagesThumbnail) {
        this.imagesThumbnail = imagesThumbnail;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoM3u8() {
        return videoM3u8;
    }

    public void setVideoM3u8(String videoM3u8) {
        this.videoM3u8 = videoM3u8;
    }

    public String getSystemTmpDir() {
        return systemTmpDir;
    }

    public void setSystemTmpDir(String systemTmpDir) {
        this.systemTmpDir = systemTmpDir;
    }

    public String getSystemDir() {
        return systemDir;
    }

    public void setSystemDir(String systemDir) {
        this.systemDir = systemDir;
    }

    public String getNewLine() {
        return newLine;
    }

    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}

