package com.ace.interceptor;

import com.util.Console;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String status = Integer.toString(response.getStatus());
        String RequestURI = request.getRequestURI();
        Console.println("status code: " + status + " -- Request uri: " + RequestURI, Console.BOLD,Console.FLUORESCENT_GREEN);
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

