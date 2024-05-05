package com.ace;

import com.ace.util.CangJieUtil;
import com.ace.utilities.ResourcesUtil;
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

        String a = ResourcesUtil.get("application-prod.yml");
        System.out.println(a);

        //InputStream inputStream = Ace.class.getResourceAsStream("/application-prod.yml");
        //if (inputStream != null) {
        //    // 在这里处理文件的输入流
        //    // 例如，使用 BufferedReader 读取文本文件
        //    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //    String line;
        //    while ((line = reader.readLine()) != null) {
        //        System.out.println(line);
        //    }
        //    reader.close();
        //
        //} else {
        //    System.out.println("无法找到文件！");
        //}
    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

