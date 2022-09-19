package com.service;

import com.util.FileUtil;
import com.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
        ImageUtil.square("C:\\ideaPorject\\ace\\src\\main\\resources\\static\\files\\images\\微信图片_202209182155335.jpg");
    }
}

