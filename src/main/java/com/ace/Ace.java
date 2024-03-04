package com.ace;

import com.ace.util.CangJieUtil;
import com.ace.util.DockerUtil;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

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
        String[] commands = {
                "echo cd to /mnt/app/",
                "cd /mnt/app/",
                "echo aaa.mp4",
                "echo bbb.mp4",
                "ffmpeg -version"
        };
      //  String command1 = "ffmpeg -version";
        dockerUtil.execute(id,commands);

    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

