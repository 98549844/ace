package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import com.ace.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: AceApplicationRestController
 * @Date: 2022/11/13 下午 11:40
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/AceApplication")
@Tag(name = "AceApplication")
public class AceApplicationRestController {
    private static final Logger log = LogManager.getLogger(AceApplicationRestController.class.getName());

    private final UserRolePermissionRestController userRolePermissionRestController;
    private final UsersService usersService;

    @Autowired
    public AceApplicationRestController(UsersService usersService, UserRolePermissionRestController userRolePermissionRestController) {
        this.userRolePermissionRestController = userRolePermissionRestController;
        this.usersService = usersService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/initDefaultUser")
    public AjaxResponse GenerateDefaultUser() {
        userRolePermissionRestController.defaultUser();

        List<Users> users = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users user : users) {
            result.add(user.getUserAccount());
            result.add(user.getStatus());
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/initRolesPermissions")
    public AjaxResponse initRolesPermissions() {
        log.info("init roles and permissions relation !!!");
        return userRolePermissionRestController.mapRolesAndPermissions();
    }


}

