package com.ace.utils;

import com.ace.controller.common.CommonController;
import com.ace.utilities.FileUtil;
import com.ace.utilities.NullUtil;
import com.ace.utilities.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * @Classname: CangJieUtil
 * @Date: 3/3/2024 2:30 am
 * @Author: garlam
 * @Description:
 */


public class CangJieUtil extends CommonController {
    private static final Logger log = LogManager.getLogger(CangJieUtil.class.getName());


    private final static String macDist = "/Users/garlam/IdeaProjects/documents/doc/input code/倉頡常用难字集.txt";
    private final static String windowsDist ="C:\\Users\\Garlam.Au\\IdeaProjects\\documents\\doc\\input code\\倉頡常用难字集.txt";

    public static void checkCangJieCode(String code) throws Exception {
        String dist;
        String osName = OsUtil.getOsName();
        if (osName.contains(OsUtil.WINDOWS)) {
            dist = windowsDist;
        } else if (osName.contains(OsUtil.MAC)) {
            dist = macDist;
        } else if (osName.contains(OsUtil.LINUX)) {
            dist = null;
        } else {
            throw new Exception("OS type: " + OsUtil.UNKNOWN);
        }


        log.info("倉頡常用难字集查詢");
        List<String> list = (List<String>) FileUtil.read(dist).get(FileUtil.LIST);
        if (NullUtil.isNull(code)) {
            //打印所有
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            //搜索
            String result = "";
            for (String s : list) {
                if (s.contains(code)) {
                    System.out.println(s);
                    result = "x";
                }
            }
            if ("".equals(result)) {
                log.info("没有查询到结果");
            }
        }
    }
}

