package com.ace;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @Classname: Ace
 * @Date: 2023/10/4 下午 02:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    final static String p = "C:\\ideaPorject\\zfile\\src\\main\\resources\\static\\aaa\\";
//    final static String p = "C:\\ace\\tmp";

    public static void main(String[] args) throws IOException {

          FileUtil.countByType(p);
          FileUtil.count(p);



    }

}

