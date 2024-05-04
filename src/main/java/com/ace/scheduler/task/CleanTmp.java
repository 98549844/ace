package com.ace.scheduler.task;

import com.ace.constant.AceEnvironment;
import com.ace.utilities.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * @Classname: CleanTempFolder
 * @Date: 3/5/24 PM9:21
 * @Author: garlam
 * @Description:
 */

@Component
public class CleanTmp {
    private static final Logger log = LogManager.getLogger(CleanTmp.class.getName());


    public void clean(String tmpPath) {
        try {
            //删除tmp文件夹,包括子文件夹和子文件
            FileUtil.deleteDirectories(tmpPath);
            //创建tmp文件夹
            FileUtil.mkDirs(tmpPath);
            log.info("Clean tmp folder: {}", tmpPath);
        } catch (Exception e) {
            log.error("Error cleaning tmp folder: {}", e.getMessage());
        }
    }


}

