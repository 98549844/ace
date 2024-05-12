package com.ace;

import com.ace.util.CangJieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws IOException {

        cangJieCheck(null);
    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

