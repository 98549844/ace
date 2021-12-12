package com.controller;

import com.constant.Constant;
import com.constant.Css;
import com.controller.common.CommonController;
import com.exception.PasswordNotMatchException;
import com.exception.UserNotFoundException;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.NullUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
//@RequestMapping("/ace")
public class LoginController extends CommonController {
    private static Logger log = LogManager.getLogger(LoginController.class.getName());

    private UsersService usersService;

    @Autowired
    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = {"/ace/login.html","/"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        if (isLogin()) {
            return super.page("ace/index.html");
        } else {
            return super.page("ace/login.html");
        }
    }

    @RequestMapping(value = "/ace/logging.html", method = RequestMethod.POST)
    public ModelAndView logging(String userAccount, String password, String rememberMe) {
        log.info("userAccount: " + userAccount);
        log.info("password: " + password);
        log.info("rememberMe: {}", rememberMe);

        ModelAndView modelAndView;
        String msg;
        Users user = new Users();
        if (userAccount.isEmpty() || password.isEmpty()) {
            //check input param
            log.error("Account/Password empty");
            modelAndView = super.page("ace/login.html");
            msg = "Account/Password empty";
            String msgCss = Css.red;
            modelAndView.addObject("msg", msg);
            modelAndView.addObject("msgCss", msgCss);
            return modelAndView;
        } else if (NullUtil.isNotNull(userAccount) && NullUtil.isNotNull(password)) {
            if(isLogin()){
                log.info("Logged into Ace");
                return super.page("ace/index.html");
            }

            user.setUserAccount(userAccount);
            user.setPassword(password);
            try {
                //get user information
                user = usersService.findByUserAccount(user);
            } catch (UserNotFoundException | PasswordNotMatchException e) {
                e.printStackTrace();
                modelAndView = super.page("ace/login.html");
                msg = "Account/Password incorrect";
                String msgCss = Css.red;
                modelAndView.addObject("msg", msg);
                modelAndView.addObject("msgCss", msgCss);
                return modelAndView;
            }
        }
        if (NullUtil.isNotNull(rememberMe)) {
            //rememberMe = on 记住我
            login(user.getUserId(), true);
        } else {
            login(user.getUserId(), false);
        }
        setSession(user);
        modelAndView = super.page("ace/index.html");
        return modelAndView;
    }
}

