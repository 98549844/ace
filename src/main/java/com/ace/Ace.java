package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.nio.file.FileSystems;

/**
 * @Classname: Ace
 * @Date: 2023/8/4 下午 12:00
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws MalformedURLException {
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources", "templates","ace","modules","report","pdf")
                .toUri()
                .toURL()
                .toString();

        System.out.println(baseUrl);

    }


}

