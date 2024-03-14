package com.ace.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.exception.PasswordNotMatchException;
import com.ace.exception.UserNotFoundException;
import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import com.ace.service.FoldersService;
import com.ace.service.ImagesService;
import com.ace.service.LoginService;
import com.ace.service.UsersService;
import com.ace.util.RegionUtil;
import com.util.DateTimeUtil;
import com.util.NullUtil;
import com.util.SqlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
public class LoginController extends CommonController {
    private static final Logger log = LogManager.getLogger(LoginController.class.getName());

    private final UsersService usersService;
    private final LoginService loginService;
    private final FoldersService foldersService;
    private final String usersFolder;
    private final String separator;
    private final ImagesService imagesService;


    @Autowired
    public LoginController(AceEnvironment aceEnvironment, LoginService loginService, UsersService usersService, FoldersService foldersService, ImagesService imagesService) {
        this.loginService = loginService;
        this.usersService = usersService;
        this.foldersService = foldersService;
        this.imagesService = imagesService;
        this.usersFolder = aceEnvironment.getUsers();
        this.separator = aceEnvironment.getSeparator();
    }


    @RequestMapping(value = {"/ace/login.html", "/"}, method = RequestMethod.GET)
    public ModelAndView login() {
        if (isLogin()) {
            return super.page("ace/index.html");
        } else {
            return super.page("ace/login.html");
        }
    }

    @RequestMapping(value = "/ace/logging.html", method = RequestMethod.POST)
    public ModelAndView logging(String userAccount, String password, String rememberMe, String deviceType) {
        log.info("userAccount: " + userAccount);
        log.info("password: " + password);
        log.info("rememberMe: {}", rememberMe);

        ModelAndView modelAndView;
        String msg;
        Users user = new Users();
        if (userAccount.isEmpty() || password.isEmpty()) {
            //check input param
            msg = "Account/Password empty";
            modelAndView = loginService.loginError(msg);
            return modelAndView;
        } else if (NullUtil.isNonNull(userAccount) && NullUtil.isNonNull(password)) {
            if (isLogin()) {
                log.info("Logged into Ace");
                return super.page("ace/index.html");
            }

            user.setUserAccount(userAccount);
            user.setPassword(password);
            try {
                //get user information
                user = usersService.findByUserAccount(user);
                long userId = user.getUserId();
                long expired = DateTimeUtil.differenceMinutesByLocalDateTime(LocalDateTime.now(), user.getExpireDate());
                if (!user.isEnabled()) {
                    msg = "Account disabled";
                    modelAndView = loginService.loginError(msg);
                    return modelAndView;
                } else if (expired < 0L) {
                    msg = "Account expired";
                    modelAndView = loginService.loginError(msg);
                    return modelAndView;
                }
                user.setLoginDateTime(LocalDateTime.now());
                user.setIp(getRequest().getRemoteAddr());
                user.setHostName(getRequest().getRemoteHost());
                user.setRegion(getRegion());
                String userIconId = getUserIcon(user.getUserAccount());
                user.setIcon(userIconId);

                //rememberMe = on 记住我
                login(userId, deviceType, NullUtil.isNonNull(rememberMe));

                log.info("UserId: {}", userId);
                //  setIcon(user);
                setUsersSaSession(user);
                usersService.save(user);
                log.info("login device: {}", StpUtil.getLoginDevice());
            } catch (UserNotFoundException | PasswordNotMatchException e) {
                e.printStackTrace();
                msg = "Account/Password incorrect";
                modelAndView = loginService.loginError(msg);
                modelAndView.addObject("userAccount", userAccount);
                return modelAndView;
            }
        }
        //modelAndView = super.page("ace/index.html");
        //创建当前用户文件夹
        String currentUserPath = usersFolder + getCurrentUser().getUserAccount() + separator;
        foldersService.createCurrentUserDefaultFolder(currentUserPath, getCurrentUser());
        getCurrentUser().setCurrentUserPath(currentUserPath);

        modelAndView = super.redirect("ace/index.html");
        return modelAndView;
    }

    private String getUserIcon(String userAccount) {
        List<Files> fs = imagesService.getFilesByFileNameLike(SqlUtil.likeRight(userAccount + "-icon"));
        if (fs.isEmpty()) {
            return "";
        }
        return fs.get(0).getFileName() + fs.get(0).getExt();
    }

    private String getRegion() {
        String ip = RegionUtil.getIpAddr(getRequest());
        //离线获取
        String region = RegionUtil.getAddr(ip);
        if (NullUtil.isNull(region)) {
            //在线获取
            region = RegionUtil.getRealAddressByIP(ip);
        }
        return region;
    }

}

