package com;

import com.util.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public void initMetaData() {

     //   ArrayList<Object> fullPath = getFilePaths("C:\\intelliJ_Project\\framework_upgrade\\AceDemoWeb\\WebContent\\files");


    }

    public static void main(String[] args) {
        System.out.println(OsUtil.getOsName());

    }

}

