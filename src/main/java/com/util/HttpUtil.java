package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @Classname: HttpUtil
 * @Date: 5/7/2021 6:49 上午
 * @Author: garlam
 * @Description:
 */


public class HttpUtil {
    private static Logger log = LogManager.getLogger(HttpUtil.class.getName());

    public static Map getHttpServletRequest(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    public static Map getRemoteInfo(HttpServletRequest request) {
        Map m = new HashMap();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        String remoteAddress = request.getRemoteAddr();
        int remotePort = request.getRemotePort();
        String protocol = request.getProtocol();
        String servletPath = request.getServletPath();

        m.put("remoteHost", remoteHost == null ? "null" : remoteHost);
        m.put("remoteUser", remoteUser == null ? "null" : remoteUser);
        m.put("remoteAddress", remoteAddress == null ? "null" : remoteAddress);
        m.put("remotePort", remotePort);
        m.put("protocol", protocol == null ? "" : protocol);
        m.put("servletPath", servletPath == null ? "null" : servletPath);
        return m;
    }

    public static String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public static void requestInfo(HttpServletRequest request) {
        String agent = request.getHeader("user-agent");
        System.out.println(agent);
        StringTokenizer st = new StringTokenizer(agent, ";");
        st.nextToken();
        String userOs = st.nextToken();
        System.out.println(userOs);
        System.out.println(System.getProperty("os.name")); //win2003竟然是win xp？
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(request.getHeader("user-agent")); //返回客户端浏览器的版本号、类型
        System.out.println(request.getMethod()); //：获得客户端向服务器端传送数据的方法有get、post、put等类型
        System.out.println(request.getRequestURI()); //：获得发出请求字符串的客户端地址
        System.out.println(request.getServletPath()); //：获得客户端所请求的脚本文件的文件路径
        System.out.println(request.getServerName()); //：获得服务器的名字
        System.out.println(request.getServerPort()); //：获得服务器的端口号
        System.out.println(request.getRemoteAddr()); //：获得客户端的ip地址
        System.out.println(request.getRemoteHost()); //：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
        System.out.println(request.getProtocol()); //：
        System.out.println(request.getHeaderNames()); //：返回所有request header的名字，结果集是一个enumeration（枚举）类的实例
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Scheme: " + request.getScheme());
        System.out.println("Server Name: " + request.getServerName());
        System.out.println("Server Port: " + request.getServerPort());
        System.out.println("Protocol: " + request.getProtocol());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Character Encoding: " + request.getCharacterEncoding());
        System.out.println("Content Length: " + request.getContentLength());
        System.out.println("Content Type: " + request.getContentType());
        System.out.println("Auth Type: " + request.getAuthType());
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Path Info: " + request.getPathInfo());
        System.out.println("Path Trans: " + request.getPathTranslated());
        System.out.println("Query String: " + request.getQueryString());
        System.out.println("Remote User: " + request.getRemoteUser());
        System.out.println("Session Id: " + request.getRequestedSessionId());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Accept: " + request.getHeader("Accept"));
        System.out.println("Host: " + request.getHeader("Host"));
        System.out.println("Referer : " + request.getHeader("Referer"));
        System.out.println("Accept-Language : " + request.getHeader("Accept-Language"));
        System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
        System.out.println("Connection : " + request.getHeader("Connection"));
        System.out.println("Cookie : " + request.getHeader("Cookie"));
    }

}

