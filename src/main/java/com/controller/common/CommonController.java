package com.controller.common;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.constant.Css;
import com.models.entity.dao.Users;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonController {
    private Log log = LogFactory.getLog(this.getClass());

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

    /** 特点: 不需要saToken登入
     * @param key
     * @param obj
     */
    protected void setHttpSession(String key, Object obj) {
        HttpSession session = getRequest().getSession();
        String sessionId = session.getId();
        log.info("setSession ID: " + sessionId);
        session.setAttribute(key, obj);
    }

    /** 特点: 不需要saToken登入
     * @param key
     * @return
     */
    protected Object getHttpSession(String key) {
        HttpSession session = getRequest().getSession();
        String sessionId = session.getId();
        log.info("getSession ID: " + sessionId);
        return session.getAttribute(key);
    }

    /** 特点: 不需要saToken登入
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
            modelAndView.addObject("user", new Users());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView(page);
        Users user = getCurrentUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        ModelAndView modelAndView = null;
        try {
            log.info("重定向地址：" + url);
            modelAndView = new ModelAndView("redirect:/" + url);
            if (isLogin()) {
                Users user = getCurrentUser();
                modelAndView.addObject("user", user);
            } else {
                modelAndView.addObject("user", new Users());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    protected void kickOut(Long userId) {
        StpUtil.kickout(userId);
    }

    /**
     * 不需要原因地被登出
     *
     * @return
     */
    protected ModelAndView logOut() {
        ModelAndView modelAndView = page("ace/login.html");
        String msg = "Logged out";
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, Css.red);
        StpUtil.logout();
        return modelAndView;
    }

    /**
     * 需要原因地被登出
     *
     * @param msg
     * @return
     */
    protected ModelAndView logOut(String msg) {
        ModelAndView modelAndView = page("ace/login.html");
        String msgCss = Css.red;
        modelAndView.addObject("msg", msg);
        modelAndView.addObject(Css.css, msgCss);
        StpUtil.logout();
        return modelAndView;
    }

    protected void setUsersSession(Users users) {
        StpUtil.getSession().set("user", users);
    }

    protected void setSession(String key, Object object) {
        StpUtil.getSession().set(key, object);
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
        SaSession session = getSession();
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
    public void login(long userId) {
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

    protected boolean isLogin() {
        return StpUtil.isLogin();
    }

}
