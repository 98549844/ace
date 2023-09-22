package com.ace;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;

/**
 * @Classname: Ace
 * @Date: 2023/8/4 下午 12:00
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws IOException, DocumentException {
        OutputStream os = new FileOutputStream("C:\\aa.pdf");
        String str = getStrByUrl("https://hk.yahoo.com/");
        System.out.println(str);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(str);
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/WINDOWS/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();

    }


    /**
     * 根據URL獲取HTML內容
     *
     * @param targeturl
     * @return
     */
    public static String getStrByUrl(String targeturl) {
        StringBuffer strs = new StringBuffer();
        try {
            URL url = new URL(targeturl);
            URLConnection URLconnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader bufr = new BufferedReader(isr);
                String str;
                while ((str = bufr.readLine()) != null) {
                    strs.append(str);
                }
                bufr.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs.toString();
    }


}

