package com.controller;

import com.controller.common.CommonController;
import com.dao.UsersDao;
import com.entity.dao.Users;
import com.service.UsersService;
import com.util.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.MapUtil;
import util.NullUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class LoginController extends CommonController {
    private static Logger log = LogManager.getLogger(LoginController.class.getName());

    private UsersService usersService;

    @Autowired
    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login.html")
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        log.info("userName: " + userName);
        log.info("password: " + password);

        String userMsg = request.getParameter("msg");
        ModelAndView modelAndView;

        //用户存在
        if ("exist".equals(userMsg)) {
            modelAndView = super.page("ace/login.html");
            modelAndView.addObject("msg", "User already exist !");
            return modelAndView;
        }

        String msg;
        Users user = new Users();
        List<Users> users ;

        if (NullUtil.isNotNull(userName) && NullUtil.isNotNull(password)) {
            user.setUserName(userName);
            user.setPassword(password);
            users = usersService.getUsers(user);
        } else {
            log.error("check input param fail!");
            modelAndView = super.page("ace/login.html");
            modelAndView.addObject("msg", "User Name or password is empty !");
            return modelAndView;
        }

        if (users != null && users.size() == 1) {
             usersService.update(users.get(0));
             log.info("user: " + users.get(0).getUserName() + " update success!");
            modelAndView = super.redirect("ace/index.html");
        } else {
            log.error("Login Fail!");
            modelAndView = super.page("ace/login.html");
            if (userName.isEmpty() || password.isEmpty()) {
                msg = "Please enter Username and Password !";
            } else {
                msg = "Login Fail, Please try again!";
            }
            modelAndView.addObject("msg", msg);
            //modelAndView.addObject("userName", userName);
        }
        return modelAndView;
    }
}

