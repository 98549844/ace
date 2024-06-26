package com.ace.utils;

import com.ace.utilities.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


/**
 * @Classname: Commands
 * @Date: 23/11/2023 7:16 pm
 * @Author: garlam
 * @Description:
 */


public class Commands {
    private static final Logger log = LogManager.getLogger(Commands.class.getName());

    public static void main(String[] args) throws IOException {
        Console.println(dockerVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(dockerRunningContainer(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(ffmpegVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
    }

    public static void versionCheck() throws IOException {
        Console.println(dockerVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);

        Console.println("Docker Running Container: ", Console.BOLD);
        Console.println(dockerRunningContainer(), Console.FLUORESCENT_PURPLE, Console.BOLD);
        Console.println(ffmpegVersion(), Console.FLUORESCENT_PURPLE, Console.BOLD);
    }


    public static String dockerVersion() throws IOException {
        return Console.execute("docker", "--version");
    }

    public static String dockerRunningContainer() throws IOException {
        String[] containers = new String[]{"docker", "ps", "--format", "\"{{.Names}} {{.Status}}\""};
        return Console.execute(containers);
    }

    public static String getRunningContainerByName(String ContainerName) throws IOException {
        String[] exec = new String[]{"docker", "ps", "--format", "\"{{.Names}} {{.Status}}\"", "--filter", "name=" + ContainerName};
        return Console.execute(exec);
    }

    public static String ffmpegVersion() throws IOException {
        String ffmpegVersion = Console.execute("ffmpeg", "-version");
        return ffmpegVersion.substring(0, 35);
    }


}

