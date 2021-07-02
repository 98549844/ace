package com.controller;

import com.controller.common.CommonController;
import com.dao.UsersDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    @RequestMapping("/login.html")
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        log.info("userName: " + userName);
        log.info("password: " + password);


        String userExist = request.getParameter("msg");
        ModelAndView modelAndView = new ModelAndView();
        //用户存在
        if ("exist".equals(userExist)) {
            modelAndView = super.page("ace/login");
            modelAndView.addObject("msg", "User already exist !");
            return modelAndView;
        }

/*        String msg;
        UserEntity user = new UserEntity();
        List<UserEntity> userEntityList = null;
        if (!userName.isEmpty() && !password.isEmpty()) {
            user.setUserName(userName);
            user.setPassword(password);
            userEntityList = userService.getUserList(user);
        }

        if (userEntityList != null && userEntityList.size() == 1) {
            userService.updateUserLoginStatus(userEntityList);
            log.info("user: " + userEntityList.get(0).getUserName() + " update success!");
            modelAndView = super.redirect("ace/index.html");
        } else {
            log.error("Login Fail!");
            modelAndView = super.page("ace/login");
            if (userName.isEmpty() || password.isEmpty()) {
                msg = "Please enter Username and Password !";
            } else {
                msg = "Login Fail, Please try again!";
            }
            modelAndView.addObject("msg", msg);
            //modelAndView.addObject("userName", userName);
        }*/

        return modelAndView;

    }
}

