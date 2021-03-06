package com.interceptor;

import com.dao.AccessLogDao;
import com.dao.UsersDao;
import com.entity.dao.AccessLog;
import com.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.IpUtil;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Classname: Interceptor
 * @Date: 28/6/2021 9:46 下午
 * @Author: garlam
 * @Description:
 */


public class Interceptor implements HandlerInterceptor {
    private static Logger log = LogManager.getLogger(Interceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String status = Integer.toString(response.getStatus());
        String RequestURI = request.getRequestURI();

        log.info("status: {} ; RequestURI: {}", status, RequestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("response.status: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // log.info("afterCompletion :【在整个请求结束之后被调用,也就是在DispatcherServlet渲染了对应的视图之后执行(主要用于资源清理工作)】");
    }


}

