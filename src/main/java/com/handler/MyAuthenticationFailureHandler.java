package com.handler;

import com.exception.CustomException;
import com.exception.CustomExceptionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.common.AjaxResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname: MyAuthenticationFailureHandler
 * @Date: 9/11/2021 11:06 下午
 * @Author: garlam
 * @Description:
 */


public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static Logger log = LogManager.getLogger(MyAuthenticationFailureHandler.class.getName());
    //在application配置文件中配置登陆的类型是JSON数据响应还是做页面响应
    @Value("${spring.security.logintype}")
    private String loginType;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码存在错误，请检查后再次登录"))));
        } else {
            response.setContentType("text/html;charset=UTF-8");
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}

