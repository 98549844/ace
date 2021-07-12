package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @Classname: PathUtil
 * @Date: 11/7/2021 3:06 上午
 * @Author: garlam
 * @Description:
 */


public class PathUtil {
    private static Logger log = LogManager.getLogger(PathUtil.class.getName());

    public static void main(String[] args) throws IOException {
        PathUtil pathUtil = new PathUtil();
        pathUtil.getResourceFile("/logback.xml");
        System.out.println();

    }

    public File getSrcFile(String srcFile) throws IOException {
        log.info("format: src/main/java/com/...");
        File file = new File(srcFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String len = null;
        while ((len = br.readLine()) != null) {
            System.out.println(len);
        }
        return file;
    }

    public File getResourceFile(String resource) throws IOException {
        log.info("read file location: ace/src/main/resources");
        log.info("format: /...");
        File file = new File(PathUtil.class.getResource(resource).getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String len = null;
        while ((len = br.readLine()) != null) {
            System.out.println(len);
        }
        return file;
    }


    public static String getSystemPath() {
        return System.getProperty("user.dir");
    }
//    public void testMethod3() throws IOException {
//        File file = new File(Thread.currentThread().getContextClassLoader().getResource("AceApplication.java").getFile());
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String len = null;
//        if ((len = br.readLine()) != null) {
//            System.out.println(len);
//        }
//    }

}

