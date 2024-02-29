package com.ace.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.net.URLConnection;


/**
 * @Classname: UrlUtil
 * @Date: 29/2/2024 10:41 am
 * @Author: garlam
 * @Description:
 */


public class UrlUtil  {
    private static final Logger log = LogManager.getLogger(UrlUtil.class.getName());

    /** 检查url是否可连接
     * @param url
     * @return
     */
    public boolean getUrlConnectionStatus(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

