package com.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @Classname: MySaTokenListener
 * @Date: 11/12/2021 4:22 AM
 * @Author: garlam
 * @Description:
 */

@Component
public class MySaTokenListener implements SaTokenListener {
    private static final Logger log = LogManager.getLogger(MySaTokenListener.class.getName());

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("tokenValue: {}", tokenValue);
        SaLoginModel saLoginModel = loginModel;
        log.info("device: {}", saLoginModel.device);
        log.info("isLastingCookie: {}", saLoginModel.isLastingCookie);
        log.info("timeout: {}", saLoginModel.timeout);
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("tokenValue: {}", tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        log.info("loginType: {}");
        log.info("loginId: {}", loginId);
        log.info("tokenValue: {}", tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("tokenValue: {}", tokenValue);
    }

    /**
     * 每次被封禁时触发
     *
     * @param loginType   账号类别
     * @param loginId     账号id
     * @param disableTime 封禁时长，单位: 秒
     */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("disableTime: {}", disableTime);
    }

    /**
     * 每次被解封时触发
     *
     * @param loginType 账号类别
     * @param loginId   账号id
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
    }

    /** 1.31
     * 每次被封禁时触发
     *
     * @param loginType   账号类别
     * @param loginId     账号id
     * @param service     指定服务
     * @param level       封禁等级
     * @param disableTime 封禁时长，单位: 秒
     */
 /*   @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("service: {}", service);
        log.info("level: {}", level);
        log.info("disableTime: {}", disableTime);

    }*/

    /** 1.31
     * 每次被解封时触发
     *
     * @param loginType 账号类别
     * @param loginId   账号id
     * @param service   指定服务
     */
   /* @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        log.info("loginType: {}", loginType);
        log.info("loginId: {}", loginId);
        log.info("service: {}", service);

    }*/


    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
        log.info("Created session id: {}", id);
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
        log.info("Logged out session id: {}", id);

    }

    /** 1.31
     * 每次Token续期时触发
     *
     * @param tokenValue token 值
     * @param loginId    账号id
     * @param timeout    续期时间
     */
   /* @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        log.info("tokenValue: {}", tokenValue);
        log.info("loginId: {}", loginId);
        log.info("timeout: {}", timeout);
    }*/


}

