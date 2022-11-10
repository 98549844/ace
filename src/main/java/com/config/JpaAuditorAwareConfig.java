package com.config;

import cn.dev33.satoken.stp.StpUtil;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Classname: JpaAuditorAwareConfig
 * @Date: 2022/9/30 上午 11:30
 * @Author: kalam_au
 * @Description:
 */


@Component
public class JpaAuditorAwareConfig implements AuditorAware<Long> {
    private static final Logger log = LogManager.getLogger(JpaAuditorAwareConfig.class.getName());


    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<Long> getCurrentAuditor() {
        boolean isLogin = StpUtil.isLogin();
        log.info("isLogin: {}", isLogin);
        if (isLogin) {
            Users user = (Users) StpUtil.getSession().get("user");
            log.info("Login user: {} ; last modify by: {}", user.getUserAccount(), user.getUserId());
            return Optional.of(user.getUserId());
        } else {
            log.info("UserId not found, set default value 0 !!!");
            return Optional.of(0l);
        }
    }
}

