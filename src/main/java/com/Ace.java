package com;

import com.util.FileUtil;
import com.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2022/8/9 上午 09:41
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws IOException {
        String a = "aaaa.bbb";
        System.out.println(a.lastIndexOf("."));
        System.out.println(a.substring(a.lastIndexOf(".")));
    }
}



