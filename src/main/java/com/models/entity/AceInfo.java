package com.models.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: AceInfo
 * @Date: 2022/7/23 上午 12:10
 * @Author: kalam_au
 * @Description:
 */


public class AceInfo {
    private static final Logger log = LogManager.getLogger(AceInfo.class.getName());


    private String ace;
    private String version;

    public AceInfo() {
        this.ace = "[ ACE ]";
        this.version = "[ 1.1 ]";
    }

    public String getAce() {
        return ace;
    }

    public void setAce(String ace) {
        this.ace = ace;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

