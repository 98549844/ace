package com.restController;

import cn.dev33.satoken.stp.StpUtil;
import com.controller.common.CommonController;
import com.models.common.AjaxResponse;
import com.models.entity.dao.Users;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import util.NullUtil;

import java.util.Optional;

/**
 * @Classname: SaTakenRestController
 * @Date: 10/12/2021 3:39 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/saToken")
@Api(tags = "saToken")
public class SaTakenRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(SaTakenRestController.class.getName());
    private UsersService usersService;

    @Autowired
    public SaTakenRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AjaxResponse doLogin(String username, String password) {
        Users users = usersService.getUsersById(1001l);
        if (NullUtil.isNotNull(users.getUserId())) {
            StpUtil.login(users.getUserId());
            return AjaxResponse.success(users.getUsername() + " login success !");
        }
        return AjaxResponse.success(users.getUsername() + " login fail !");
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    public String isLogin() {
        return "当前session是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public AjaxResponse apiInfo() {
        // 标记当前会话登录的账号id
        // 建议的参数类型：long | int | String， 不可以传入复杂类型，如：User、Admin等等
        Users users = usersService.getUsersById(1001l);
        StpUtil.login(users.getUserId());
        log.info("login success {} ", users.getUserId());

        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
        log.info("isLogin {} ",StpUtil.isLogin());

        // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();
        log.info("checkLogin success");

        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
        log.info("getLoginId {} ", StpUtil.getLoginId());

        // 类似查询API还有：
        log.info("String {} ", StpUtil.getLoginIdAsString());    // 获取当前会话账号id, 并转化为`String`类型
        log.info("int {} ", StpUtil.getLoginIdAsInt());       // 获取当前会话账号id, 并转化为`int`类型
        log.info("Long {} ", StpUtil.getLoginIdAsLong());      // 获取当前会话账号id, 并转化为`long`类型
        // 获取当前会话账号id, 如果未登录，则返回null
        log.info("getLoginIdDefaultNull {}", StpUtil.getLoginIdDefaultNull());

        // 获取当前会话账号id, 如果未登录，则返回默认值 （`defaultValue`可以为任意类型）
        RequestAttributes request = RequestContextHolder.getRequestAttributes();
        log.info("getSessionId {} ", StpUtil.getLoginId(request.getSessionId()));


        // 获取当前`StpLogic`的token名称
        String tokenName = StpUtil.getTokenName();
        log.info("tokenName {} ", tokenName);

        // 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();
        log.info("tokenValue: {} ", tokenValue);

        // 获取指定token对应的账号id，如果未登录，则返回 null
        log.info("getLoginIdByToken {} ", StpUtil.getLoginIdByToken(tokenValue));


        // 获取当前会话的token信息参数
        log.info("getTokenInfo {} ", StpUtil.getTokenInfo());

        // 当前会话注销登录
        StpUtil.logout();
        log.info("logout success");
        return AjaxResponse.success();
    }

}

