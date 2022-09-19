package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @Classname: MsicUtil
 * @Date: 2022/9/19 下午 02:52
 * @Author: kalam_au
 * @Description:
 */


public class MsicUtil {
    private static final Logger log = LogManager.getLogger(MsicUtil.class.getName());

    public static void replaceSymbol(String file) throws IOException {
        log.info("将全角符号转半角");
        FileUtil.rewrite(file, FileUtil.ToDBC(FileUtil.getText(file)));
    }


    public static void main(String[] args) {
        String a = "！@#￥%（）。，";
        String x = a.replace("！", "!").replace("（", "(").replace("）", ")").replace("。", ".").replace("，", ",");
 /*       x = a.replace("（", "(");
        x = a.replace("）", ")");
        x = a.replace("。", ".");
        x = a.replace("，", ",");*/
        System.out.println(x);

        String b = "abc";
        String c = b.replace("b", "F");
        System.out.println(c);
    }

}

