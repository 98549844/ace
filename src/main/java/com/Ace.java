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
        int k = 0;
        for (int i = 0, j = 0; i < 10; i++, j = j + 2) {
            k = j;
        }
        System.out.println("k = " + k);
    }

}

