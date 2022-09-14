package com.restController;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: BrowserRestController
 * @Date: 2022/9/13 下午 12:13
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/browser")
@Api(tags = "browser")
public class BrowserRestController {
    private static final Logger log = LogManager.getLogger(BrowserRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public void getElement(@RequestParam(value = "url") String url) {
        log.info("url: {}", url);
        //如果没有配置环境变量，需要调用
        //第一个参数固定写法，第二个参数是chromedriver的安装位置
        System.setProperty("webdriver.chrome.driver", "doc/chrome/windows/chromedriver_105.exe");
        try {
            //设置不弹出浏览器
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("disable-gpu");
            //Chrome设置代理
            /*if (zsyzbConfig.proxyHost != null && !zsyzbConfig.proxyHost.equals("") && zsyzbConfig.proxyPort != null) {
                options.addArguments("--proxy-server=http://" + proxyIP);
            }*/
            ChromeDriver driver = new ChromeDriver(options);
            // driver.get("http://localhost:8088/");
            driver.get(url);
            //获取页面源码
            String content = driver.getPageSource();
            //使用jsoup解析页面
            Document document = Jsoup.parse(content);

            log.info("getElementsByClass(Class属性)");
            String classTxt = document.getElementsByClass("win").text();
            log.info("classTxt: {}", classTxt);

            log.info("getElementById(ID属性)");
            Element element = document.getElementById("i_cecream");
            log.info("element: {}", element.text());

            //js选择器: https://www.w3school.com.cn/js/js_jquery_selectors.asp
            Elements select = document.getElementById("i_cecream").select(".bili-feed4");
            log.info("select: {}", select.text());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

