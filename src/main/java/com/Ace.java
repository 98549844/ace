package com;

import com.util.FileUtil;
import com.util.ImageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2022/8/9 上午 09:41
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws IOException {
        List f = FileUtil.getFileNames("src/main/resources/static/files/images");

        for (int i = 0; i < f.size(); i++) {

            ImageUtil.square("src/main/resources/static/files/images/" + f.get(i));
            ImageUtil.compress("src/main/resources/static/files/images/temp/" + f.get(i));
        }
    }


}

