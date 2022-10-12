package com.service;

import com.constant.AceEnvironment;
import com.models.entity.dao.Files;
import com.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
    private final String imagePath;
    private final String imagePathTemp;

    @Autowired
    public GalleryService(FilesService filesService) {
        this.filesService = filesService;
        this.imagePath = AceEnvironment.getImagesPath();
        this.imagePathTemp = AceEnvironment.getImagesTemp();
    }

    public List getImages() throws IOException {
        String src = imagePath;
        String temp = imagePathTemp;
        log.info("image location: {}", src);

        List<String> ls = FileUtil.getFileNames(src);
        List<String> tempLs = FileUtil.getFileNames(temp);

        Map mp = ListUtil.getDeduplicateElements(ls, tempLs);
        compressImages((List<String>) mp.get(ListUtil.LIST_1));
        tempLs = (List<String>) mp.get(ListUtil.LIST_2);
        if (NullUtil.isNotNull(tempLs) && tempLs.size() > 0) {
            for (String s : tempLs) {
                FileUtil.delete(imagePathTemp + s);
            }
        }
        //根据folder实际文件控制数据库, 删除folder不存文件数据
        List<String> fName = FileUtil.getNames(ls);
        List<Files> filesList = filesService.findFilesByPathAndFileNameNotIn(imagePath , fName );
        filesService.deleteAll(filesList);
        return FileUtil.getNamesOrderByLastModifiedDate(temp, true);
    }

    private void compressImages(List<String> ls) {
        log.info("temp images expired, compressing image ...");
        if (NullUtil.isNull(ls) || ls.size() == 0) {
            return;
        }
        try {
            ImageUtil imageUtil = new ImageUtil();
            for (String name : ls) {
                imageUtil.square(imagePath + name);
                ImageUtil.compress(imagePathTemp + name);
            }
        } catch (Exception e) {
            log.error("Include non image files !!!");
            e.printStackTrace();
        }
        log.info("compressing image complete !!!");
    }

    public Files rotate(String direction, String uuid) throws Exception {
        int rotate;
        if ("left".equals(direction)) {
            rotate = -90;
        } else {
            rotate = 90;
        }
        String newUuid = UUID.get();
        Files f = filesService.findFilesByFileName(uuid);
        rename(f.getLocation(), imagePath + newUuid + f.getExt());

        String temp = imagePathTemp + f.getFileName() + f.getExt();
        String newTemp = imagePathTemp + newUuid + f.getExt();

        f.setFileName(newUuid);
        f.setRemark("Ace Application UUID: " + newUuid);
        ImageUtil.rotation(temp, newTemp, rotate);

        filesService.delFile(temp);
        return filesService.saveAndFlush(f);

    }

    private static void rename(String src, String desc) throws Exception {
        log.info("Start rename file");
        // 旧的文件或目录
        File oldName = new File(src);
        // 新的文件或目录1
        File newName = new File(desc);
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new java.io.IOException("target file exists !!!");
        }
        if (oldName.renameTo(newName)) {
            log.info("File renamed success => {}" , desc);
        } else {
            log.error("File rename fail !!!");
        }
    }
}

