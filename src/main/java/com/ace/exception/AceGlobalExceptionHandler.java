package com.ace.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.ace.constant.Css;
import com.ace.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

//      HTTP            错误列表
//		HTTP 400        请求无效
//		HTTP 401.1      未授权: 登录失败
//		HTTP 401.2      未授权: 服务器配置问题导致登录失败
//		HTTP 401.3      ACL 禁止访问资源
//		HTTP 401.4      未授权: 授权被筛选器拒绝
//		HTTP 401.5      未授权: ISAPI 或 CGI 授权失败
//		HTTP 403        禁止访问
//		HTTP 403        对 Internet 服务管理器 (HTML) 的访问仅限于 Localhost
//		HTTP 403.1      禁止访问: 禁止可执行访问
//		HTTP 403.2      禁止访问: 禁止读访问
//		HTTP 403.3      禁止访问: 禁止写访问
//		HTTP 403.4      禁止访问: 要求 SSL
//		HTTP 403.5      禁止访问: 要求 SSL 128
//		HTTP 403.6      禁止访问: IP 地址被拒绝
//		HTTP 403.7      禁止访问: 要求客户证书
//		HTTP 403.8      禁止访问: 禁止站点访问
//		HTTP 403.9      禁止访问: 连接的用户过多
//		HTTP 403.10     禁止访问: 配置无效
//		HTTP 403.11     禁止访问: 密码更改
//		HTTP 403.12     禁止访问: 映射器拒绝访问
//		HTTP 403.13     禁止访问: 客户证书已被吊销
//		HTTP 403.15     禁止访问: 客户访问许可过多
//		HTTP 403.16     禁止访问: 客户证书不可信或者无效
//		HTTP 403.17     禁止访问: 客户证书已经到期或者尚未生效
//		HTTP 404.1      无法找到 Web 站点
//		HTTP 404        无法找到文件
//		HTTP 405        资源被禁止
//		HTTP 406        无法接受
//		HTTP 407        要求代理身份验证
//		HTTP 410        永远不可用
//		HTTP 412        先决条件失败
//		HTTP 414        请求  URI 太长
//		HTTP 500        内部服务器错误
//		HTTP 500.100    内部服务器错误  ASP 错误
//		HTTP 50011      服务器关闭
//		HTTP 50012      应用程序重新启动
//		HTTP 50013      服务器太忙
//		HTTP 50014      应用程序无效
//		HTTP 50015      不允许请求 global.asa
//		Error 501       未实现
//		HTTP 502        网关错误


/**
 * 统一异常处理
 *
 * @Classname: AceGlobalExceptionHandler
 * @Date: 12/12/2021 11:58 PM
 * @Author: garlam
 * @Description:
 */


@RestController
@ControllerAdvice
public class AceGlobalExceptionHandler extends CommonController implements ErrorController {
    private static final Logger log = LogManager.getLogger(AceGlobalExceptionHandler.class.getName());

    private static final String NotLoginException = "cn.dev33.satoken.exception.NotLoginException";

    private void logException(String message, String stackTrace) {
        log.warn("URL: " + super.getRequest().getRequestURL().toString());
        log.warn("HTTP_METHOD: " + super.getRequest().getMethod());
        log.warn("error code: {}", super.getResponse().getStatus());
        log.warn("Exception message: {}", message);
        if (stackTrace.contains(NotLoginException)) {
            log.warn("Exception stackTrace: {}", NotLoginException);
        } else {
            log.error("Exception stackTrace: {}", stackTrace);
        }
    }


//    关于/error的调用, 它是一个特殊的错误处理路径,
//    用于处理应用程序中的错误和异常. 当应用程序抛出未处理的异常时,
//    Spring Boot会自动将请求重定向到/error路径.
//    通过自定义@ControllerAdvice和定义一个处理/error路径的方法, 可以实现自定义的错误处理逻辑.
//
//    步骤:
//    创建一个@ControllerAdvice类, 用于处理全局的异常和错误.
//    在该类中, 使用@ExceptionHandler注解定义方法来处理特定类型的异常.
//    在该类中, 使用@ModelAttribute注解定义方法来添加全局模型属性.
//    创建一个处理/error路径的方法, 并在方法中进行自定义的错误处理逻辑.
//    在@ControllerAdvice类上添加@RequestMapping("/error")注解, 将该类映射到/error路径.
//    通过这样的设置, 当应用程序抛出未处理的异常时, 请求将被重定向到/error路径, 并由自定义的错误处理方法进行处理.

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ModelAndView error() {
        String requestURL = super.getRequest().getRequestURL().toString();
        ModelAndView modelAndView;
        int status = super.getResponse().getStatus();

        switch (status) {
            case 404 -> {
                log.info("{} => error: {} [page not found]", requestURL, status);
                String warningMsg = "Page Not Found";
                modelAndView = exceptionModelAndView("ace/error", Css.faSitemap, status, warningMsg);
                modelAndView.addObject("exceptionMsg", "page not found");
            }
            case 401 -> {
                log.info("{} => error: {} [user not login]", requestURL, status);
                String warningMsg = "User Not Login";
                modelAndView = exceptionModelAndView("ace/error", Css.faSitemap, status, warningMsg);
                modelAndView.addObject("exceptionMsg", "user not login");
            }
            case 500 -> {
                log.info("{} error: {} [internal server error]", requestURL, status);
                String warningMsg = "Something Went Wrong";
                modelAndView = exceptionModelAndView("ace/error", Css.faRandom, status, warningMsg);
                modelAndView.addObject("exceptionMsg", "server internal error");
            }
            default -> {
                log.info("{} error: {}", requestURL, status);
                String warningMsg = "Occur Unknown exception";
                modelAndView = exceptionModelAndView("ace/error", Css.faGear, status, warningMsg);
                modelAndView.addObject("exceptionMsg", "UNKNOWN EXCEPTION");
            }
        }
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView modelAndView;
        if (e instanceof NotLoginException) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String stackTrace = sw.toString();
            String message = e.getMessage();
            logException(message, stackTrace);

            modelAndView = new ModelAndView("ace/login");
            if (message.contains("token 已被踢下线")) {
                modelAndView.addObject("msg", "Account kicked out");
            } else if (message.contains("token 无效")) {
                modelAndView.addObject("msg", "Session invalid");
            } else if (message.contains("token 已被顶下线")) {
                modelAndView.addObject("msg", "Account forced to logout");
            } else if (message.contains("未能读取到有效token ")) {
                modelAndView.addObject("msg", "Session illegal");
            } else if (message.contains("token 已过期")) {
                modelAndView.addObject("msg", "Session timeout");
            }
            modelAndView.addObject(Css.css, Css.red);
            return modelAndView;
        } /*else if(e instanceof ClientAbortException){
            return null;}*/ else {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String stackTrace = sw.toString();
            logException(e.getMessage(), stackTrace);

            String warningMsg = "<strong> OCCUR EXCEPTION </strong>";
            //返回错误页面
            modelAndView = exceptionModelAndView("ace/error", Css.faGear, 999, warningMsg);
            String exceptionMsg = "<strong style=\"color: red\">" + e.getMessage() + "</strong>" + "<br><br>" + stackTrace;
            modelAndView.addObject("exceptionMsg", exceptionMsg);
            return modelAndView;
        }
    }


    private ModelAndView exceptionModelAndView(String url, String faCss, int status, String warningMsg) {
        ModelAndView modelAndView = new ModelAndView(url);
        modelAndView.addObject("faCss", faCss);
        modelAndView.addObject("httpStatus", status);
        modelAndView.addObject("warningMsg", warningMsg);
        return modelAndView;
    }

    /*    @ExceptionHandler(value = NullPointerException.class)
        @ResponseBody
        public ModelAndView exceptionHandler(NullPointerException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String stackTrace = sw.toString();
            exceptionLog(e.getMessage(),stackTrace );

            int status =super.getResponse().getStatus();
            String warningMsg = "Occur unknown exception";
            ModelAndView modelAndView = exceptionModelAndView("ace/error", Css.faGear, status, warningMsg);
            modelAndView.addObject("exceptionMsg", e.getMessage() + "<br><br>" + stackTrace);

            return modelAndView;
        }

        @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
        @ResponseBody
        public ModelAndView httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String stackTrace = sw.toString();
            exceptionLog(e.getMessage(),stackTrace );

            int status =super.getResponse().getStatus();
            String warningMsg = "Occur HttpRequestMethodNotSupportedException";
            ModelAndView modelAndView = exceptionModelAndView("ace/error", Css.faGear, status, warningMsg);
            modelAndView.addObject("exceptionMsg", e.getMessage() + "<br><br>" + stackTrace);

            return modelAndView;
        }
    */
    //未提供token
    //token无效
/*    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public ModelAndView notLoginExceptionHandler(NotLoginException e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String stackTrace = sw.toString();
        String message = e.getMessage();
        exceptionLog(message, stackTrace);

        ModelAndView modelAndView = new ModelAndView("ace/login");
        if (message.contains("Token已被踢下线")) {
            modelAndView.addObject(Css.css, Css.red);
            modelAndView.addObject("msg", "Account kicked out");
        }
        return modelAndView;
    }*/


}

