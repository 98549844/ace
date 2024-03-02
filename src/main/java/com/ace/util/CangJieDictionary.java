package com.ace.util;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;

import java.io.IOException;
import java.util.List;


/**
 * @Classname: CangJieDictionary
 * @Date: 3/3/2024 2:30 am
 * @Author: garlam
 * @Description:
 */


public class CangJieDictionary extends CommonController {
    private static final Logger log = LogManager.getLogger(CangJieDictionary.class.getName());


    private final static String dist = "/Users/garlam/IdeaProjects/ace/doc/input code/倉頡常用难字集.txt";

    public static void checkCangJieCode(String code) throws IOException {
        log.info("倉頡常用难字集查詢");
        List<String> list = (List<String>) FileUtil.read(dist).get(FileUtil.LIST);
        for (String s : list) {
            if (s.contains(code)) {
                System.out.println(s);
            }
        }
    }
}

