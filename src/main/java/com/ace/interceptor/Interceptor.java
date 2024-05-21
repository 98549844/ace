package com.ace.interceptor;

import com.ace.util.IpUtil;
import com.ace.utilities.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Classname: Interceptor
 * @Date: 28/6/2021 9:46 下午
 * @Author: garlam
 * @Description:
 */

public class Interceptor implements HandlerInterceptor {
    private static final Logger log = LogManager.getLogger(Interceptor.class.getName());


    // https://mp.weixin.qq.com/s/N8EpeV7geQzbICLw__wQ9w
    // Spring Boot 防止重复提交
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        int status = response.getStatus();
        String RequestURI = request.getRequestURI();
        String clientIp = IpUtil.getIpAddr(request);

        Console.println("clientIp: " + clientIp + ":" + request.getRemotePort() + " - status code: " + status + " - Request uri: " + RequestURI, Console.BOLD, Console.FLUORESCENT_GREEN);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //  log.info("response.status: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //preHandle返回true后, afterCompletion方法会执行

    }


}

