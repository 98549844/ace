package com.ace.util;

import com.util.Console;
import com.util.ConsoleTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Classname: ComponentUtil
 * @Date: 23/11/2023 7:16 pm
 * @Author: garlam
 * @Description:
 */


public class ComponentUtil {
    private static final Logger log = LogManager.getLogger(ComponentUtil.class.getName());

    public static void main(String[] args) throws IOException {
        Console.println(dockerVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(dockerRunningContainer(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(ffmpegVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
    }

    public static void versionCheck() throws IOException {
        Console.println(dockerVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);

        Console.println("Docker Running Container: ",  Console.BOLD);
        Console.println(dockerRunningContainer(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(ffmpegVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
    }


    public static String dockerVersion() throws IOException {
        return Console.execute("docker", "--version");
    }

    public static String dockerRunningContainer() throws IOException {
        //--是特殊符号, 要折分处理
        String[] formatPs = new String[]{"docker", "ps", "--format", "\"{{.Names}} {{.Status}}\""};
        return Console.execute(formatPs);
    }

    public static String ffmpegVersion() throws IOException {
        return Console.execute("ffmpeg", "-version");
    }


}

