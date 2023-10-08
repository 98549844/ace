package com.ace;

import com.tools.ContentTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        String p = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/exception/AceGlobalExceptionHandler.java";
        ContentTool.replace(p);
    }
}

