package com.service;

import com.constant.AceEnvironment;
import com.models.entity.dao.Files;
import com.util.FileUtil;
import com.util.ImageUtil;
import com.util.ListUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.util.UUID;

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
        List<Files> filesList = filesService.findFilesByFileNameNotInOrderByLastUpdateDateDesc(fName);
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

    public void rotate(String direction, String src) throws IOException {
        int rotate;
        if ("left".equals(direction)) {
            rotate = -90;
        } else {
            rotate = 90;
        }
        Files f = filesService.findFilesByFileName(src);
        //  String newUUID = UUID.get();
        String desc = imagePathTemp + f.getFileName() + f.getExt();
        ImageUtil.rotation(desc, desc, rotate);
        // f.setFileName(newUUID);
        // filesService.save(f);


    }


}

