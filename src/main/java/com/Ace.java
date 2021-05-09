package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());
    // private final static Log log = LogFactory.getLog(Ace.class);

    public static void main(String[] args) throws Exception {
        File file = new File(".");
        File files = new File("file/image/txt.txt");
        System.out.println("当前jvm虚拟机启动位置的绝对路径为：" + file.getAbsoluteFile());
        System.out.println("当前jvm虚拟机启动位置的绝对路径为：" + file.getPath());

        System.out.println(files.getAbsolutePath());
        System.out.println(files.getPath());
    }
}
