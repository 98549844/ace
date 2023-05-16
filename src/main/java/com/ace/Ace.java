package com.ace;

import com.ace.util.IpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws UnknownHostException {
//        log.error( java.net.getByName("api.coinmarketcap.com" ) );
        IpUtil.getDomainIp("api.coinmarketcap.com");
        System.out.println(IpUtil.getHostName());

        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("ip: "+ip);

        //  JSONObject result = RestUtil.get_StrToJson("https://13.226.120.9/data-api/v3/cryptocurrency/quote/latest?id=1&convertld=2792",10000,null);
    }

}


