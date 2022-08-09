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
import util.DateTimeUtil;
import util.MapUtil;
import util.NullUtil;

import java.time.LocalDateTime;
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
    private final RolesRestController rolesRestController;
    private final PermissionRestController permissionRestController;


    public UserRolePermissionRestController(RolePermissionsService rolePermissionsService, UserRolesService userRolesService, UsersService usersService, RolesService rolesService, PermissionsService permissionsService, RolesRestController rolesRestController, PermissionRestController permissionRestController) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.permissionsService = permissionsService;
        this.userRolesService = userRolesService;
        this.rolePermissionsService = rolePermissionsService;
        this.rolesRestController = rolesRestController;
        this.permissionRestController = permissionRestController;
    }

    public void checkDefaultUser() {
        Users admin = usersService.findByUserAccount("admin");
        if (NullUtil.isNull(admin) && NullUtil.isNull(admin.getUserAccount())) {
            log.warn("administrator was DESTROYED");
            addDefaultAdminUsers();
            log.info("administrator rebuild success !!!");
            return;
        }
        log.info("default administrator account health check: PASS");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addDefaultUsers")
    public AjaxResponse addDefaultAdminUsers() {
        rolesService.findAll();
        permissionsService.findAll();
        if (0 == rolesService.findAll().size() || 0 == permissionsService.findAll().size()) {
            log.warn("roles or permission is empty, rebuild default roles and permission ...");
            relateRolesAndPermissions();
            log.info("rebuild roles and permission complete !");
        }

        //related default user into roles
        Roles adminRoles = rolesService.findByRoleCode(Constant.ROLECODE_ADMIN);

        //create default user
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");

        if (NullUtil.isNull(admin)) {
            admin = new Users();
            admin.setPassword("$2a$11$gNnG0zfbKr8c7M3YX9Frn.HaKPS1hFsmgKPt4F6LXnEmmE0FAhV8C");//password 909394
            admin.setUserAccount("admin");
            admin.setUsername("administrator");
            admin.setDescription(Constant.administrator);
            admin.setEmail("admin@ace.com");
            admin.setMobile("0000 0000");
            admin.setGender(null);
            admin.setBirthday(LocalDateTime.now());
            admin.setLoginDateTime(LocalDateTime.now());
            admin.setStatus(Constant.ACTIVE);
            admin.setDomain("ace.com");
            admin.setIp("127.0.0.1");
            admin.setDomain("ace.com");
            admin.setRemark("ACE APPLICATION");
            admin.setEnabled(true);
            usersService.saveAndFlush(admin);
            UserRoles adminUsersRoles = new UserRoles();
            adminUsersRoles.setRoleId(adminRoles.getRoleId());
            adminUsersRoles.setUserId(admin.getUserId());
            userRolesService.save(adminUsersRoles);
        }

        if (NullUtil.isNull(garlam)) {
            garlam = new Users();
            garlam.setPassword("$2a$11$gNnG0zfbKr8c7M3YX9Frn.HaKPS1hFsmgKPt4F6LXnEmmE0FAhV8C");
            garlam.setUserAccount("garlam");
            garlam.setUsername("garlam");
            garlam.setDescription(Constant.administrator);
            garlam.setEmail("garlam@ace.com");
            garlam.setMobile("0000 0000");
            garlam.setGender(null);
            garlam.setBirthday(LocalDateTime.now());
            garlam.setLoginDateTime(LocalDateTime.now());
            garlam.setStatus(Constant.ACTIVE);
            garlam.setDomain("ace.com");
            garlam.setIp("127.0.0.1");
            garlam.setDomain("ace.com");
            garlam.setRemark("ACE APPLICATION");
            garlam.setEnabled(true);
            usersService.saveAndFlush(garlam);
            UserRoles garlamUsersRoles = new UserRoles();
            garlamUsersRoles.setRoleId(adminRoles.getRoleId());
            garlamUsersRoles.setUserId(garlam.getUserId());
            userRolesService.save(garlamUsersRoles);

        }
        return AjaxResponse.success("User: administrator and garlam has been created and added into roles");
    }


    /**
     * 关联用户组别和权限级别
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/relateRolesAndPermissions")
    public AjaxResponse relateRolesAndPermissions() {

        if (0 == rolesService.findAll().size() && 0 == permissionsService.findAll().size()) {
            log.info("Clean ROLES and PERMISSION DATA ...");
            rolesService.deleteAll();
            permissionsService.deleteAll();
            log.info("ROLES and PERMISSION COMPLETED !");

            log.info("Build ROLES and PERMISSION data ...");
            rolesRestController.insertRoles();
            permissionRestController.insertPermission();
            log.info("Build ROLES and PERMISSION COMPLETE !");
        } else {
            int rSize = rolesService.findAll().size();
            int pSize = permissionsService.findAll().size();

            log.info("Roles size: {}", rolesService.findAll().size());
            log.info("Permission size: {}", permissionsService.findAll().size());
            StringBuilder c = new StringBuilder();
            c.append("Rebuild roles permission fail !!! ");
            c.append("Roles size: " + rSize);
            c.append("Permission size: " + pSize);
            return AjaxResponse.success(c.toString());
        }


        Roles roleAdmin = rolesService.findByRoleCode(Constant.ROLECODE_ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Constant.ROLECODE_USER);
        Roles RoleDisable = rolesService.findByRoleCode(Constant.ROLECODE_DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Constant.ROLECODE_VIEWER);
        List<Permissions> permissionsList = permissionsService.findAll();


        RolePermissions all = new RolePermissions();
        RolePermissions insert = new RolePermissions();
        RolePermissions update = new RolePermissions();
        RolePermissions delete = new RolePermissions();
        RolePermissions select = new RolePermissions();
        RolePermissions deny = new RolePermissions();


        for (Permissions permission : permissionsList) {

            if (permission.isEnabled()) {
                switch (permission.getPermissionCode()) {
                    case Constant.ALL:
                        all.setPermissionsId(permission.getPermissionsId());
                        all.setRoleId(roleAdmin.getRoleId());

                        rolePermissionsService.save(all);
                        break;
                    case Constant.ROLECODE_USER:
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleUser.getRoleId());

                        update.setPermissionsId(permission.getPermissionsId());
                        update.setRoleId(roleUser.getRoleId());

                        insert.setPermissionsId(permission.getPermissionsId());
                        insert.setRoleId(roleUser.getRoleId());

                        rolePermissionsService.save(select);
                        rolePermissionsService.save(update);
                        rolePermissionsService.save(insert);
                        break;
                    case Constant.ROLECODE_VIEWER:
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleViewer.getRoleId());

                        rolePermissionsService.save(select);
                        break;
                    case Constant.ROLECODE_DISABLE:
                        deny.setPermissionsId(permission.getPermissionsId());
                        deny.setRoleId(RoleDisable.getRoleId());

                        rolePermissionsService.save(deny);
                        break;

                }
            }
        }
        return AjaxResponse.success("Roles Permission merged");
    }


    /**
     * 整理没有用户组别的用户
     *
     * @return
     */
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

