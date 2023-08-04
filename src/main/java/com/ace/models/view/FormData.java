package com.ace.models.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: FormData
 * @Date: 2023/8/3 下午 05:55
 * @Author: kalam_au
 * @Description:
 */


public class FormData {
    private static final Logger log = LogManager.getLogger(FormData.class.getName());

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

