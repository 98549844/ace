package com.ace.controller.common;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.ace.constant.Css;
import com.ace.models.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

public class CommonController {
    private final Log log = LogFactory.getLog(this.getClass());

    /**
     * Request对象(存在于用户的每个请求)
     */
    private HttpServletResponse response;
    private HttpServletRequest request;

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
     * 特点: 不需要saToken登入
     *
     * @param key
     * @param obj
     */
    protected void setHttpSession(String key, Object obj) {
        HttpSession session = getRequest().getSession();
        String sessionId = session.getId();
        log.info("setSession ID: " + sessionId);
        session.setAttribute(key, obj);
    }

    /**
     * 特点: 不需要saToken登入
     *
     * @param key
     * @return
     */
    protected Object getHttpSession(String key) {
        HttpSession session = getRequest().getSession();
        String sessionId = session.getId();
        log.info("getSession ID: " + sessionId);
        return session.getAttribute(key);
    }

    /**
     * 特点: 不需要saToken登入
     *
     * @param key
     */
    protected void removeHttpSession(String key) {
        HttpSession session = getRequest().getSession();
        session.removeAttribute(key);
    }

    /**
     * 返回页面
     *
     * @param page page
     * @return mv
     */
    protected ModelAndView page(String page) {
        if (!isLogin()) {
            ModelAndView modelAndView = new ModelAndView("ace/login.html");
            modelAndView.addObject("currentUser", new Users());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView(page);
        Users user = getCurrentUser();
        modelAndView.addObject("currentUser", user);
        return modelAndView;
    }

    /**
     * 不需要登陆并返回页面
     *
     * @param page page
     * @return mv
     */
    protected ModelAndView pageWithOutLogin(String page) {
        return new ModelAndView(page);
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = null;
        try {
            log.info("重定向地址：" + url);
            modelAndView = new ModelAndView("redirect:/" + url);
            if (isLogin()) {
                Users user = getCurrentUser();
                modelAndView.addObject("currentUser", user);
            } else {
                modelAndView.addObject("currentUser", new Users());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    protected void kickout(Long userId) {
        StpUtil.kickout(userId);
    }

    /**
     * 不需要原因地被登出
     *
     * @return
     */
    protected ModelAndView logout() {
        ModelAndView modelAndView = page("ace/login.html");
        String msg = "Logged out";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.red);
        StpUtil.logout();
        return modelAndView;
    }

    protected void logout(long userId, String device) {
        StpUtil.logout(userId, device);
    }

    /**
     * 需要原因地被登出
     *
     * @param msg
     * @return
     */
    protected ModelAndView logout(String msg) {
        ModelAndView modelAndView = page("ace/login.html");
        String msgCss = Css.red;
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, msgCss);
        StpUtil.logout();
        return modelAndView;
    }

    protected void setUsersSaSession(Users users) {
        StpUtil.getSession().set("user", users);
        // setHttpSession("user", users); // springboot3 set不进去, 有问题未解决
    }

    protected void setSession(String key, Object object) {
        StpUtil.getSession().set(key, object);
        setHttpSession(key, object);
    }

    protected SaSession getSession() {
        return StpUtil.getSession();
    }

    protected String getTokenValue() {
        return StpUtil.getTokenValue();
    }

    protected Users getCurrentUser() {
        return (Users) StpUtil.getSession().get("user");
    }

    protected void clearSession() {
        // 注销此Session会话 (从持久库删除此Session)
        SaSession session = StpUtil.getSession();
        session.delete("user");
        session.logout();
    }

    protected void clearSession(String key) {
        // 注销此Session会话 (从持久库删除此Session)
        SaSession session = getSession();
        session.delete(key);
        session.logout();
    }

    protected SaSession getSessionByLoginId(Long id) {
        return StpUtil.getSessionByLoginId(id);
    }

    /**
     * 不记住登入
     *
     * @param userId
     */
    protected void login(long userId) {
        StpUtil.login(userId);
    }

    /**
     * 记住我登入
     *
     * @param userId
     * @param rememberMe
     */
    protected void login(long userId, boolean rememberMe) {
        StpUtil.login(userId, rememberMe);
    }

    protected void login(long userId, String device) {
        StpUtil.login(userId, device);
    }

    protected void login(long userId, String device, boolean rememberMe) {
        SaLoginModel saLoginModel = new SaLoginModel();
        saLoginModel.setDevice(device);
        saLoginModel.setIsLastingCookie(rememberMe);
        StpUtil.login(userId, saLoginModel);
    }

    protected String getDevice() {
        return StpUtil.getLoginDevice();
    }

    protected boolean isLogin() {
        return StpUtil.isLogin();
    }

}
