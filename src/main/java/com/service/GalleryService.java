package com.service;

import com.util.FileUtil;
import com.util.ImageUtil;
import com.util.ListUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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


    public void squareImages(String src) throws IOException {
        List<String> imagesList = FileUtil.getFileNames(src);

        for (String name : imagesList) {
            ImageUtil.square(src + name);
        }
    }


    public static void main(String[] args) throws IOException {
        String src = "src/main/resources/static/files/images/";
        String temp = "src/main/resources/static/files/images/temp/";
        Map m = ListUtil.getDeduplicateElements(FileUtil.getFileNames(src),FileUtil.getFileNames(temp));





    }
}

