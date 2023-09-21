package com.ace;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2023/8/4 下午 12:00
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {

        FileUtil fileUtil = new FileUtil();
        List a = fileUtil.getFilePaths("C:\\ideaPorject\\ace");

        System.out.println("List a "+a.size());
        for (Object s : a) {
            System.out.println(s.toString());
        }

        System.out.println("------------------------------");

        List b = fileUtil.getFilePaths("C:\\ideaPorject\\ace");
        System.out.println("List b "+b.size());
        for (Object s : b) {
            System.out.println(s.toString());
        }

    }


}

