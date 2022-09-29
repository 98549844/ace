package com.constant;

import com.models.entity.dao.Files;
import com.util.Console;
import com.util.FileUtil;
import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Classname: AceEnvironment
 * @Date: 19/7/2021 12:26 下午
 * @Author: garlam
 * @Description:
 */

@Component
public class AceEnvironment {
    private static Logger log = LogManager.getLogger(AceEnvironment.class.getName());

    //container of application.yml value
    public static Environment environment;
    private static String filePath;
    private static String imagesPath;
    private static String filesTemp;
    private static String imagesTemp;


    @Autowired
    public AceEnvironment(Environment environment) {
        AceEnvironment.environment = environment;
    }

    public static void setUp() {
        folderSetUp();
    }

    private static void folderSetUp() {
        if (OsUtil.getOsName().contains(OsUtil.WINDOWS)) {
            String fsPath = "C:\\ACE\\files\\";
            String imgPath = "C:\\ACE\\images\\";
            String temp = "temp\\";

            setFilePath(fsPath);
            setFilesTemp(fsPath + temp);
            setImagesPath(imgPath);
            setImagesTemp(imgPath + temp);

            FileUtil.mkDirs(fsPath + temp);
            FileUtil.mkDirs(imgPath + temp);

            String msg = "ACE environment folder setup complete on Windows !!!";
            Console.println(msg, Console.BLUE, Console.BOLD);
        } else if (OsUtil.getOsName().contains(OsUtil.MAC)) {
            String fsPath = "TODO";
            String imgPath = "TODO";
            String temp = "TODO";

            setFilePath(fsPath);
            setFilesTemp(fsPath + temp);
            setImagesPath(imgPath);
            setImagesTemp(imgPath + temp);

            FileUtil.mkDirs(fsPath);
            FileUtil.mkDirs(imgPath);

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
}

