package com.ace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {
        try {
            String[] command = {"docker", "ps"};


            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();
            Process proc = pb.start();

            InputStream is = proc.getInputStream();
            OutputStream os = proc.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write("pwd");
            writer.flush();
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

