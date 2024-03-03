package com.ace;

import com.ace.util.CangJieUtil;
import com.ace.util.DockerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        DockerUtil dockerUtil = new DockerUtil();
        // dockerUtil.getVersion();
        // dockerUtil.getImages();
        // dockerUtil.getContainers();
        String id = dockerUtil.getContainerId("ffmpeg");
       // System.out.println(id);

        String command = "ffmpeg -version";
      //  String command1 = "ffmpeg -version";
        dockerUtil.execute(id,command);

    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

