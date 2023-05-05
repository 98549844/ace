package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {
        int a = 10;
        addOne(a);
        System.out.println(a); // 输出 10


        Integer b = 10;
        addOne(b);
        System.out.println(b); // 输出 11
    }

    public static void addOne(int num) {
        num++;
    }
}


