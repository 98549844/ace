package com.ace;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname: Ace
 * @Date: 2023/10/4 下午 02:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    // final static String p = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/Ace.java";
    final static String p = "/Users/garlam/ace/images/6c1aa51c-0a59-4dcb-8c18-8ac655e79a1f.jpeg";

    public static void getCreateDate() throws IOException {
        // 指定自己的目标文件
        File file = new File(p);
        // 根据文件的绝对路径获取Path
        Path path = Paths.get(file.getAbsolutePath());
        // 根据path获取文件的基本属性类
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        // 从基本属性类中获取文件创建时间
        FileTime fileTime = attrs.creationTime();
        // 将文件创建时间转成毫秒
        long millis = fileTime.toMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(millis);
        // 毫秒转成时间字符串
        String time = dateFormat.format(date);
        System.out.println(time);
    }

    public static void getLastAccessDate() throws IOException {
        // 指定自己的目标文件
        File file = new File(p);
        // 根据文件的绝对路径获取Path
        Path path = Paths.get(file.getAbsolutePath());
        // 根据path获取文件的基本属性类
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        // 从基本属性类中获取文件创建时间
        FileTime fileTime = attrs.lastAccessTime();
        // 将文件创建时间转成毫秒
        long millis = fileTime.toMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(millis);
        // 毫秒转成时间字符串
        String time = dateFormat.format(date);
        System.out.println(time);
    }

    public static void getLastModifiedDate() throws IOException {
        // 指定自己的目标文件
        File file = new File(p);
        // 根据文件的绝对路径获取Path
        Path path = Paths.get(file.getAbsolutePath());
        // 根据path获取文件的基本属性类
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        // 从基本属性类中获取文件创建时间
        FileTime fileTime = attrs.lastModifiedTime();
        // 将文件创建时间转成毫秒
        long millis = fileTime.toMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(millis);
        // 毫秒转成时间字符串
        String time = dateFormat.format(date);
        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        getLastModifiedDate();
        getLastAccessDate();
        getCreateDate();
    }

    //读取文件大小
    //https://blog.csdn.net/cn_lyg/article/details/103784912/
}

