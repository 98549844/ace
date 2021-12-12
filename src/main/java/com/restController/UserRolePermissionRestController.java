package com.restController;

import com.constant.Constant;
import com.models.common.AjaxResponse;
import com.models.entity.dao.*;
import com.service.*;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.controller.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @Classname: UserRolePermissionRestController
 * @Date: 12/12/2021 6:32 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/userRolePermission")
@Api(tags = "userRolePermission")
public class UserRolePermissionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(UserRolePermissionRestController.class.getName());

    private UsersService usersService;
    private RolesService rolesService;
    private PermissionsService permissionsService;
    private UserRolesService userRolesService;
    private RolePermissionsService rolePermissionsService;

    public UserRolePermissionRestController(RolePermissionsService rolePermissionsService, UserRolesService userRolesService, UsersService usersService, RolesService rolesService, PermissionsService permissionsService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.permissionsService = permissionsService;
        this.userRolesService = userRolesService;
        this.rolePermissionsService = rolePermissionsService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/merge")
    public AjaxResponse merge() {
        userRolesService.deleteAll();
        List<Users> users = usersService.findAll();

        Roles roleAdmin = rolesService.findByRoleCode(Constant.ROLECODE_ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Constant.ROLECODE_USER);
        Roles RoleDisable = rolesService.findByRoleCode(Constant.ROLECODE_DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Constant.ROLECODE_VIEWER);


        List<UserRoles> userRolesList = new ArrayList<>();
        int userSize = users.size();
        for (int i = 0; i < userSize; i++) {
            UserRoles userRoles = new UserRoles();
            switch (users.get(i).getDescription()) {
                case "Administrator":
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleAdmin.getRoleId());
                    break;
                case "Disable":
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(RoleDisable.getRoleId());
                    break;
                case "User":
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleUser.getRoleId());
                    break;
                case "Viewer":
                    userRoles.setUserId(users.get(i).getUserId());
                    userRoles.setRoleId(roleViewer.getRoleId());
                    break;
            }
            userRolesList.add(userRoles);
        }
        userRolesService.saveAll(userRolesList);


        rolePermissionsService.deleteAll();

        List<Roles> rolesList = rolesService.findAll();
        int rolesSize = rolesList.size();
        Permissions p0 = permissionsService.findPermissionsByPermissionCode("0");
        Permissions p10 = permissionsService.findPermissionsByPermissionCode("10");
        Permissions p8 = permissionsService.findPermissionsByPermissionCode("8");
        Permissions p4 = permissionsService.findPermissionsByPermissionCode("4");

        List<RolePermissions> permissionsArrayList = new ArrayList<>();
        for (int i = 0; i < rolesSize; i++) {
            RolePermissions rolePermissions = new RolePermissions();
            switch (rolesList.get(i).getRoleCode()) {
                case "ADMIN":
                    rolePermissions.setRoleId(rolesList.get(i).getRoleId());
                    rolePermissions.setPermissionsId(p0.getPermissionsId());
                    break;
                case "DISABLE":
                    rolePermissions.setRoleId(rolesList.get(i).getRoleId());
                    rolePermissions.setPermissionsId(p10.getPermissionsId());
                    break;
                case "USER":
                    rolePermissions.setRoleId(rolesList.get(i).getRoleId());
                    rolePermissions.setPermissionsId(p8.getPermissionsId());
                    break;
                case "VIEWER":
                    rolePermissions.setRoleId(rolesList.get(i).getRoleId());
                    rolePermissions.setPermissionsId(p4.getPermissionsId());
                    break;
            }
            permissionsArrayList.add(rolePermissions);
        }
        rolePermissionsService.saveAll(permissionsArrayList);

        return AjaxResponse.success("User Roles Permission merged");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermission")
    public AjaxResponse findUserRolePermission() {
        return AjaxResponse.success(usersService.findUserRolePermission());
    }

}

