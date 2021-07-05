package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
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

        m.put("remoteHost", remoteHost == null ? "--" : remoteHost);
        m.put("remoteUser", remoteUser == null ? "--" : remoteUser);
        m.put("remoteAddress", remoteAddress == null ? "--" : remoteAddress);
        m.put("remotePort", remotePort);
        m.put("protocol", protocol == null ? "" : protocol);
        m.put("servletPath", servletPath == null ? "--" : servletPath);

        return m;
    }


}

