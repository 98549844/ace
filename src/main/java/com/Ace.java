package com;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.VersionUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @Classname: Ace
 * @Date: 2022/8/9 上午 09:41
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws IOException {
        Map m =  FileUtil.read("C:\\ideaPorject\\ace\\doc\\mybatis\\mybatis cache.txt");
        m.get(FileUtil.ORIGINAL);
        System.out.println(m.get(FileUtil.ORIGINAL));
    }


}

