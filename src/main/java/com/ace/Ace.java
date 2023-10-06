package com.ace;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {
        String p = "C:\\ace\\images";
        List<String> a = FileUtil.getFileNamesWithExt(p);

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}

