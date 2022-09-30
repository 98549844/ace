package com.service;

import com.constant.AceEnvironment;
import com.dao.FilesDao;
import com.models.entity.dao.Files;
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

    private FilesService filesService;

    public GalleryService(FilesService filesService) {
        this.filesService = filesService;
    }

    public List getImages() throws IOException {
        String src = AceEnvironment.getImagesPath();
        String temp = AceEnvironment.getImagesTemp();

        log.info("image location: {}", src);

        List<String> ls = FileUtil.getFileNames(src);
        List<String> tempLs = FileUtil.getFileNames(temp);

        if (ls.size() > tempLs.size()) {
            Map mp = ListUtil.getDeduplicateElements(ls, tempLs);
            tempLs = (List<String>) mp.get(ListUtil.LIST_1);
            compressImages(tempLs);
        } else if (ls.size() < tempLs.size()) {
            FileUtil.deletes(temp);
            compressImages(ls);
        }

        //根据folder实际文件控制数据库
        List<Files> filesList = filesService.findFilesByFileNameNotIn(ls);
        filesService.deleteAll(filesList);
        return FileUtil.getFileNames(temp);
    }

    private void compressImages(List<String> ls) {
        // remove 时会出现重名情况
        log.info("temp images expired, compressing image ...");
        String src = AceEnvironment.getImagesPath();
        String temp = AceEnvironment.getImagesTemp();

        try {
            for (String name : ls) {
                ImageUtil.square(src + name);
                ImageUtil.compress(temp + name);
            }
        } catch (Exception e) {
            log.error("Include non image files !!!");
            e.printStackTrace();
        }
        log.info("compressing image complete !!!");
    }


}

