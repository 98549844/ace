package com.ace.utils;

import com.ace.utilities.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import java.util.ArrayList;
import java.util.List;

import static com.ace.tools.MavenTool.*;

/**
 * @Classname: SpringUtil
 * @Date: 2022/8/4 上午 11:44
 * @Author: kalam_au
 * @Description:
 */


public class VersionUtil {
    private static final Logger log = LogManager.getLogger(VersionUtil.class.getName());


    public static void main(String[] args) {
        showSpringFrameworkVersion();
        showSpringbootVersion();
    }

    public static void showSpringFrameworkVersion() {
        Console.println("Spring Framework Version: " + SpringVersion.getVersion(), Console.BOLD);
    }

    public static void showSpringbootVersion() {
        Console.println("Spring Boot Version: " + SpringBootVersion.getVersion(), Console.BOLD);
    }

    public static void getThymeleafVersion() {
        String command = "dependency:list -DincludeArtifactIds=thymeleaf";
        List<String> commands = new ArrayList<>();
        commands.add(mavenWindowsHome);
        commands.add(command);
        run(preparedCommands(commands));
    }

}

