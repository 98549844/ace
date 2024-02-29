package com.ace;

import com.ace.util.UrlUtil;
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


    public static void main(String[] args) {
        //  UrlUtil.getUrlConnectionStatus("http://ocsp.apple.com/ocsp02-wwdr01");
        //  UrlUtil.readFileByUrl("http://ocsp.apple.com/ocsp02-wwdr01");

        UrlUtil.readFileContentByUrl("http://www.apache.org/licenses/LICENSE-2.0");
    }
}

