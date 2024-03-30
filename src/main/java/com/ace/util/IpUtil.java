package com.ace.util;

import com.ace.constant.constant;
import com.ace.utilities.MapUtil;
import com.ace.utilities.NullUtil;
import jakarta.servlet.http.HttpServletRequest;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
//@Component("自定义beanName")
@Component
public class IpUtil implements ApplicationListener<WebServerInitializedEvent> {

    static private final Log log = LogFactory.getLog(IpUtil.class);

    public static int Port;
    public static String ip;
    public static String hostName;
    public static String domain;


    public static void main(String[] args) {
        IpUtil ip = new IpUtil();
        Map m = ip.getHostInfo();
        MapUtil.iterateMapKeySet(m);
    }

    public static String getHostName() {
        IpUtil u = new IpUtil();
        Map m = u.getHostInfo();
        hostName = (String) m.get("hostName");
        return hostName;
    }

    /**
     * 根据ip获取详细地址
     */
    public String getRegion(String ip) {
        final Ip2regionSearcher ip2regionSearcher = ApplicationContextUtil.getBean(Ip2regionSearcher.class);
        IpInfo ipInfo = ip2regionSearcher.memorySearch(ip);
        if (!NullUtil.isNull(ipInfo)) {
            return ipInfo.getAddress();
        }
        return "No information found !";
    }

    public static String getDomainIp(String domain) throws UnknownHostException {
        String domainIp = String.valueOf(java.net.InetAddress.getByName(domain));
        log.info("domain information: " + domainIp);
        return String.valueOf(java.net.InetAddress.getByName(domain));
    }

    public Map getHostInfo() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ip = address.getHostAddress();
        hostName = address.getHostName();
        domain = address.getHostName();

        Map m = new HashMap();

        m.put("ip", ip);
        m.put("port", Port);
        m.put("hostName", hostName);
        m.put("domain", domain);

        return m;
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        Port = event.getWebServer().getPort();
    }

    /**
     * 获取 IP地址
     * 使用 Nginx等反向代理软件， 则不能通过 request.getRemoteAddr()获取 IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
     * X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals(constant.LOCAL_IP)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error(e.getMessage(), e);
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }

        return ipAddress;
    }

}
