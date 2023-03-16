package com.ace.controller;

import com.ace.models.entity.Roles;
import com.ace.models.entity.UserRoles;
import com.ace.models.entity.Users;
import com.ace.service.UserRolesService;
import com.ace.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @Classname: UserRolesController
 * @Date: 3/1/2022 1:41 AM
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class UserRolesController extends CommonController {
    private static final Logger log = LogManager.getLogger(UserRolesController.class.getName());

    private final UsersService usersService;
    private final UserRolesService userRolesService;

    @Autowired
    public UserRolesController(UsersService usersService, UserRolesService userRolesService) {
        this.usersService = usersService;
        this.userRolesService = userRolesService;
    }

    @RequestMapping(value = "/updateUserRoles.html", method = RequestMethod.POST)
    public ModelAndView registration(Long userId, List<Roles> roles) {
        ModelAndView modelAndView = new ModelAndView();
        Users users = usersService.findUsersById(userId);

        //delete user roles
        List<UserRoles> userRoles = userRolesService.findAllByUserId(userId);
        for (UserRoles userRole : userRoles) {
            userRolesService.delete(userRole);
        }

        //insert user roles
        for (Roles role : roles) {
            UserRoles userRole = new UserRoles();
            userRole.setRoleId(role.getRoleId());
            userRole.setUserId(users.getUserId());
            userRolesService.save(userRole);
        }

        return modelAndView;
    }
}

