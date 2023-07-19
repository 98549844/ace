package com.ace;

import com.util.FastJson2Util;
import com.util.SleepUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {
        String BTC = "http://svrtest000.mmatrix.io/api/000/20220210/quote/btc/";
        String ETH = "http://svrtest000.mmatrix.io/api/000/20220210/quote/ethhkd/";
        String USDT = "http://svrtest000.mmatrix.io/api/000/20220210/quote/usdthkd/";
        try {
            String response1 = sendGetRequest(BTC);
            String response2 = sendGetRequest(ETH);
            String response3 = sendGetRequest(USDT);
            System.out.println(FastJson2Util.formatJson(response1));
            System.out.println(FastJson2Util.formatJson(response2));
            System.out.println(FastJson2Util.formatJson(response3));

            SleepUtil.sleep(1);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static String sendGetRequest(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        /*try {*/
        URLConnection conn = new URL(url).openConnection();
        //   URLConnection conn = null;
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        /*} catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }*/
        return sb.toString();
    }


}


