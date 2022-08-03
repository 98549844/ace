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
import util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

    private final UsersService usersService;
    private final RolesService rolesService;
    private final PermissionsService permissionsService;
    private final UserRolesService userRolesService;
    private final RolePermissionsService rolePermissionsService;

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
        //用户加入角色
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
        Permissions p1 = permissionsService.findPermissionsByPermissionCode(Constant.INSERT);
        Permissions p2 = permissionsService.findPermissionsByPermissionCode(Constant.UPDATE);
        Permissions p3 = permissionsService.findPermissionsByPermissionCode(Constant.DELETE);
        Permissions p4 = permissionsService.findPermissionsByPermissionCode(Constant.SELECT);
        Permissions p10 = permissionsService.findPermissionsByPermissionCode(Constant.DENY);




        for (int i = 0; i < rolesSize; i++) {
            RolePermissions insert = new RolePermissions();
            RolePermissions update = new RolePermissions();
            RolePermissions delete = new RolePermissions();
            RolePermissions select = new RolePermissions();
            RolePermissions deny = new RolePermissions();

            switch (rolesList.get(i).getRoleCode()) {
                case "ADMIN":
                    insert.setRoleId(rolesList.get(i).getRoleId());
                    insert.setPermissionsId(p1.getPermissionsId());

                    update.setRoleId(rolesList.get(i).getRoleId());
                    update.setPermissionsId(p2.getPermissionsId());

                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    delete.setRoleId(rolesList.get(i).getRoleId());
                    delete.setPermissionsId(p3.getPermissionsId());

                    rolePermissionsService.save(insert);
                    rolePermissionsService.save(update);
                    rolePermissionsService.save(delete);
                    rolePermissionsService.save(select);

                    break;
                case "DISABLE":
                    deny.setRoleId(rolesList.get(i).getRoleId());
                    deny.setPermissionsId(p10.getPermissionsId());

                    rolePermissionsService.save(deny);

                    break;
                case "USER":
                    insert.setRoleId(rolesList.get(i).getRoleId());
                    insert.setPermissionsId(p1.getPermissionsId());

                    update.setRoleId(rolesList.get(i).getRoleId());
                    update.setPermissionsId(p2.getPermissionsId());

                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(insert);
                    rolePermissionsService.save(update);
                    rolePermissionsService.save(select);
                    break;
                case "VIEWER":
                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(select);
                    break;
            }
        }
        return AjaxResponse.success("User Roles Permission merged");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermission")
    public AjaxResponse findUserRolePermission() {
        List<Map> list = usersService.findUserRolePermission();

        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeyset(map);
        }

        return AjaxResponse.success(usersService.findUserRolePermission());
    }

}

