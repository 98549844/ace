package com.service;

import com.util.FileUtil;
import com.util.ImageUtil;
import com.util.ListUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname: GalleryService
 * @Date: 2022/9/19 上午 09:40
 * @Author: kalam_au
 * @Description:
 */


@Service
public class GalleryService {
    private static final Logger log = LogManager.getLogger(GalleryService.class.getName());


    public List getImages() throws IOException {
        log.info("default image location: resources/static/files/images/");

        String src = "src/main/resources/static/files/images/";
        String temp = "src/main/resources/static/files/images/temp/";
        FileUtil.mkDirs(src);
        FileUtil.mkDirs(temp);

        List<String> ls = FileUtil.getFileNames(src);
        List<String> tempLs = FileUtil.getFileNames(temp);

        if (ls.size() != tempLs.size()) {
            Map mp = ListUtil.getDeduplicateElements(ls, tempLs);
            tempLs = (List<String>) mp.get(ListUtil.LIST_1);
            log.info("temp images expired, compressing image ...");
            try {
                for (String name : tempLs) {
                    ImageUtil.square(src + name);
                    ImageUtil.compress(temp + name);
                }
            } catch (Exception e) {
                log.error("Include non image files !!!");
                e.printStackTrace();
            }
            log.info("compressing image complete !!!");
        }

        return FileUtil.getFileNames(temp);
    }


}

