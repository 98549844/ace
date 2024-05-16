package com.ace;

import com.ace.util.CangJieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws IOException {

        try {
            // 在这里抛出异常
            throw new NullPointerException("空指针异常");
        } catch (NullPointerException e) {
            e.printStackTrace();
            // 创建一个字符串输出流
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            // 将异常堆栈跟踪信息写入字符串输出流
            e.printStackTrace(printWriter);
            printWriter.flush();

            // 从字符串输出流中获取堆栈跟踪信息
            String stackTrace = stringWriter.toString();

            // 在这里可以对堆栈跟踪信息进行处理
            System.out.println(stackTrace);
        }
    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

