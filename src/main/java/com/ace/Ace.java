package com.ace;

import com.util.FileUtil;
import com.util.MapUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        String s = "/Users/garlam/IdeaProjects/ace/src/main/resources/static/assets/css";
        //  String s = "/Users/garlam/IdeaProjects/ace/src/main/resources/static/assets";

        FileUtil fileUtil = new FileUtil();
        Map a = fileUtil.countByType(s);
     //   fileUtil.countByType(s,"css","jpg","jj");
        System.out.println("-----");

    }
}

