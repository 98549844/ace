package com.controller.common;

import com.constant.WebServiceInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonController {
    private Log log = LogFactory.getLog(this.getClass());
    protected final String keyAjaxResult = "ajaxResult";
    /**
     * Request对象(存在于用户的每个请求)
     */
    protected HttpServletResponse response;
    protected HttpServletRequest request;

    protected HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = servletRequestAttributes.getRequest();
        return request;
    }

    protected HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        response = servletRequestAttributes.getResponse();
        return response;
    }



    /**
     * 返回页面
     *
     * @param page page
     * @return mv
     */
    protected ModelAndView page(String page) {
        return new ModelAndView(page);
    }


    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = null;
        try {
            log.info("重定向地址：" + url);
            modelAndView = new ModelAndView("redirect:/" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 返回json数据
     *
     * @param
     * @return
     */
    protected ModelAndView json(Integer status, String msg, Object obj) {
        WebServiceInfo webServiceInfo = new WebServiceInfo(status, msg, obj);
        ModelAndView json = json(webServiceInfo);
        return json;
    }

    /**
     * 返回json数据
     *
     * @param rs
     * @return
     */
    protected ModelAndView json(WebServiceInfo rs) {
        ModelAndView mv = new ModelAndView("pb-pages/ajax-result.jsp");
        System.out.println(JsonUtil.getInstance().toJson(rs));
        mv.addObject(keyAjaxResult, JsonUtil.getInstance().toJson(rs));
        return mv;
    }

}
