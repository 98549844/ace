package com.ace.models.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;


/**
 * @Classname: NotificationRequest
 * @Date: 1/3/2024 10:02 am
 * @Author: garlam
 * @Description:
 */


public class NotificationRequest extends CommonController {
    private static final Logger log = LogManager.getLogger(NotificationRequest.class.getName());

    private String title;
    private String body;
    private String token;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

