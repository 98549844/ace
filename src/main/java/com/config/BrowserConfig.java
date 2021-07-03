package com.config;


import com.util.ApplicationContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import util.Console;
import util.DataTypeUtil;
import util.IpUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

/**
 * Application启动时自动打开默认Browser
 */
@Configuration
public class BrowserConfig {
    private static final Log log = LogFactory.getLog(BrowserConfig.class);

    static String url = "http://localhost:8088/";
    static String SwaggerUrl = "http://localhost:8088/swagger-ui.html";
    static String windowsBrowser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe ";

    /**
     * 打开默认Browser
     */

    public void openWindowsDefaultBrowser() {
        try {
            ProcessBuilder proc = new ProcessBuilder(windowsBrowser, url);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("WELCOME PAGE: ", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openSwaggerOnMac(Map m, boolean isSwaggerEnable) throws IOException {
        if (!isSwaggerEnable) {
            return;
        }
        String macSwaggerUrl = SwaggerUrl.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
        String Command = "open " + macSwaggerUrl;
        log.info("Swagger2:\t\t" + SwaggerUrl);
        Process Child = Runtime.getRuntime().exec(Command);
    }


    public void openMacDefaultBrowser(boolean indexEnable) throws IOException {
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
        log.info("Home Page:\t\t" + macUrl);
        if (true) {
            String Command = "open " + macUrl;
            Process Child = Runtime.getRuntime().exec(Command);
        }

    }

    public void getCss() throws IOException {
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
        log.info("Home Page:\t\t" + macUrl+ "assets/css/bootstrap.min.css");
        if (true) {
            String Command = "open " + macUrl + "assets/css/bootstrap.min.css";
            Process Child = Runtime.getRuntime().exec(Command);
        }
    }

    public void getIndex() throws IOException {
        ApplicationContextUtil app = new ApplicationContextUtil();
        IpUtil ip = (IpUtil) app.getBeanByName("ipUtil");
        Map m = ip.getHostInfo();
        String macUrl = url.replace("8088", DataTypeUtil.integerToString((Integer) m.get("port")));
        log.info("Home Page:\t\t" + macUrl+ "ace/index.html");
        if (true) {
            String Command = "open " + macUrl + "ace/index.html";
            Process Child = Runtime.getRuntime().exec(Command);
        }
    }

    /**
     * 打开默认Browser
     */
    public void openSwaggerPage() {
        try {
            ProcessBuilder proc = new ProcessBuilder(windowsBrowser, SwaggerUrl);
            proc.start();
            BrowserConfig config = new BrowserConfig();
            config.PrintUrl("SWAGGER:\t\t", SwaggerUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PrintUrl(String banner, String url) {
        System.out.print(LocalDateTime.now() + "  ");
        Console.print("INFO ", Console.GREEN);
        Console.println(banner + url, Console.BOLD, Console.BLUE);
    }

    public boolean getOsInfo() {
        //Java获取当前操作系统的信息
        //https://blog.csdn.net/qq_35981283/article/details/73332040
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        System.out.println("操作系统称种类：" + props.getProperty("os.name"));
        if (osName != null && osName.toLowerCase().contains("windows")) {
            return true;
        } else {
            return false;
        }
    }

}