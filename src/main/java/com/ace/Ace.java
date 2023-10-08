package com.ace;

import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        String s = "/Users/garlam/IdeaProjects/ace/src/main/resources/static/assets";
        // String s = "/Users/garlam/IdeaProjects/ace/src/main/resources/static/assets/fonts";
        FileUtil f = new FileUtil();
       /* List ls = f.getFilePaths(s);
        List list = FileUtil.getFolderAndSubFolderList(s);

        for (Object location : list) {
            log.info(location.toString());
            FileUtil.countByType(location.toString());
        }*/
        Map a = FileUtil.countByTypeWithSubFolders(s);
        System.out.println("-----");

    }
}

