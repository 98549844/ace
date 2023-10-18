package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        System.out.println("姓名\t\t年龄\t城市");
        System.out.println("John\t\t25\tNew York");
        System.out.println("Emily\t\t30\tLondon");
        System.out.println("Michael\t\t35\tBerlin");
    }
}

