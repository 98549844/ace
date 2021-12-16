package com.controller;


import com.constant.Css;
import com.controller.common.CommonController;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.NullUtil;

import java.util.List;

/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace/users")
public class UserController extends CommonController {
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    private UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        List<Users> userList = usersService.findUsersOrderByLoginDateTime(15);

        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        //submenu css control
        modelAndView.addObject(Css.open, Css.open);
        return modelAndView;
    }


    @RequestMapping(value = "/enable.html", method = RequestMethod.GET)
    public ModelAndView setEnable(@RequestParam(value = "userId") Long userId) {
        log.info("userId {}", userId);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");

        Users user = usersService.findUsersById(userId);
        if (user.isEnabled()) {
            user.setEnabled(false);
            modelAndView.addObject("ajaxResult", "<strong class=\"red\">Disable</strong>");
        } else {
            user.setEnabled(true);
            modelAndView.addObject("ajaxResult", "<strong class=\"green\">Enable</strong>");
        }
        usersService.save(user);
        kickOut(user.getUserId());
        return modelAndView;
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public ModelAndView getProfile() {
        ModelAndView modelAndView = super.page("ace/modules/users/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public ModelAndView getUserById(String username) {
        log.info("username: {}", username);
        List<Users> userList;
        if (NullUtil.isNull(username) || "".equals(username)) {
            userList = usersService.findUsersOrderByLoginDateTime(30);
        } else {
            userList = usersService.findUsersByUsernameLikeIgnoreCaseOrderByLoginDateTime(username);
        }
        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        //submenu css control
        modelAndView.addObject(Css.open, Css.open);
        return modelAndView;
    }

    @RequestMapping(value = "/delete.html", method = RequestMethod.GET)
    public ModelAndView deleteUser() {
        ModelAndView modelAndView = super.page("ace/modules/users/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/getUserRolePermission.html", method = RequestMethod.GET)
    public ModelAndView getUserRolePermission() {
        List<Object[]> list = usersService.findUserRolePermission();

        for (Object[] obj : list) {
            System.out.println("--------------");
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
            System.out.println(obj[3]);
            System.out.println(obj[4]);
            System.out.println(obj[5]);
            System.out.println(obj[6]);
            System.out.println(obj[7]);
            System.out.println(obj[8]);
        }
        ModelAndView modelAndView = super.page("ace/modules/users/profile");
        return modelAndView;
    }

}
