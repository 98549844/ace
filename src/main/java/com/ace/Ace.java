package com.ace;

import com.ace.util.RegionUtil;
import com.util.FileUtil;
import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {
        String p = System.getProperty("user.dir") + "/src/main/java/com/ace/";
        String name = "result.txt";
        String aaa = "ggg" + PathUtil.getNewLine();
        // 从 0.0.0.0 到 255.255.255.255
        System.out.println("start");
        StringBuilder re = new StringBuilder();
        for (int i = 0; i <= 255; i++) {
            for (int j = 0; j <= 255; j++) {
                for (int k = 0; k <= 255; k++) {
                    for (int l = 0; l <= 255; l++) {
                        String ipv4Address = i + "." + j + "." + k + "." + l;
                        String region = RegionUtil.getAddr(ipv4Address);
                        if (!region.contains("内网IP")) {
                            re.append(region).append(PathUtil.getNewLine());
                        }
                    }
                }
            }
        }
        FileUtil.write(p, name, re, true);
        System.out.println("end");

    }


}

