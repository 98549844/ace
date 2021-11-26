package com.controller;

import com.controller.common.CommonController;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RegistrationController extends CommonController {
    private static Logger log = LogManager.getLogger(RegistrationController.class.getName());

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/registration.html")
    public ModelAndView login(@ModelAttribute Users users) {
        ModelAndView modelAndView;

        if (!usersService.validate(users)) {
            log.error("users information incorrect");
            modelAndView = super.redirect("ace/login.html?msg=error");
            modelAndView.addObject("msg", "information error");
            return modelAndView;
        }

        Integer count = usersService.countByUserAccountOrEmail(users);
        if (count == 0) {
            usersService.accountRegistration(users);
            log.info("新建用户：" + users.getUserAccount());
            modelAndView = super.page("ace/index.html");
        } else {
            modelAndView = super.redirect("ace/login.html?msg=exist");
            modelAndView.addObject("msg", "User exist");
        }
        return modelAndView;
    }
}

