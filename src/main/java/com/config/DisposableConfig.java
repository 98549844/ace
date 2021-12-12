package com.config;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;


/**
 * @Classname: DisposableConfig
 * @Date: 13/12/2021 4:30 AM
 * @Author: garlam
 * @Description:
 */

@Component
public class DisposableConfig implements DisposableBean {
    private static final Logger log = LogManager.getLogger(DisposableConfig.class.getName());


    @Override
    public void destroy() throws Exception {
        log.info("Shutting down AceApplication ... ");
        // 注销此Session会话 (从持久库删除此Session)
       // StpUtil.getSession().logout();
       // StpUtil.logout();
        log.info("AceApplication shutdown completed");
    }
}

