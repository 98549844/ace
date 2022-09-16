package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.VersionUtil;

/**
 * @Classname: Ace
 * @Date: 2022/8/9 上午 09:41
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {
        Integer i = 4;
        int j = 5;
        String s = "hello";
        boolean b = true;
        System.out.println(getType(b));//java.lang.Boolean
        System.out.println(s.getClass().getName());//java.lang.String
        System.out.println(i.getClass().getName());//java.lang.Integer
        System.out.println(getType(j));//java.lang.Integer

        System.out.println(getSimpleType(b));//java.lang.Boolean
        System.out.println(s.getClass().getSimpleName());//java.lang.String
        System.out.println(i.getClass().getSimpleName());//java.lang.Integer
        System.out.println(getSimpleType(j));//java.lang.Integer
    }

    public static String getType(Object o) {
        return o.getClass().getName();
    }

    public static String getSimpleType(Object o) {
        return o.getClass().getSimpleName();
    }

}

