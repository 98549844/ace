package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.exception.ResponseException;
import com.ace.models.common.RespResult;
import com.ace.models.entity.*;
import com.ace.service.*;
import com.ace.utilities.MapUtil;
import com.ace.utilities.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;


/**
 * @Classname: UserRolePermissionRestController
 * @Date: 12/12/2021 6:32 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/userRolePermission")
@Tag(name = "UserRolePermission")
public class UserRolePermissionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(UserRolePermissionRestController.class.getName());

    private final UsersService usersService;
    private final RolesService rolesService;
    private final PermissionsService permissionsService;
    private final UserRolesService userRolesService;
    private final RolePermissionsService rolePermissionsService;
    private final RolesRestController rolesRestController;
    private final PermissionRestController permissionRestController;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserRolePermissionRestController(PasswordEncoder passwordEncoder, RolePermissionsService rolePermissionsService, UserRolesService userRolesService, UsersService usersService, RolesService rolesService, PermissionsService permissionsService, RolesRestController rolesRestController, PermissionRestController permissionRestController) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.permissionsService = permissionsService;
        this.userRolesService = userRolesService;
        this.rolePermissionsService = rolePermissionsService;
        this.rolesRestController = rolesRestController;
        this.permissionRestController = permissionRestController;
        this.passwordEncoder = passwordEncoder;
    }

    public void defaultUser() {
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");
        if (NullUtil.isNull(admin) || NullUtil.isNull(garlam)) {
            log.warn("administrator/garlam was DESTROYED");
            addDefaultAdminUsers();
            log.info("administrator/garlam rebuild success !!!");
            return;
        }
        log.info("default ADMIN account health check: PASS");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addDefaultUsers")
    public RespResult addDefaultAdminUsers() {
        if (rolesService.findAll().isEmpty() || permissionsService.findAll().isEmpty()) {
            log.warn("roles or permission is empty, rebuild default roles and permission ...");
            buildRolesPermissions();
            log.info("rebuild roles and permission complete !");
        }

        //related default user into roles
        Roles adminRoles = rolesService.findByRoleCode(Roles.ADMIN);

        //create default user
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");

        if (NullUtil.isNull(admin)) {
            admin = new Users();
            admin.setPassword(passwordEncoder.encode("admin"));//password admin
            admin.setUserAccount("admin");
            admin.setUsername("administrator");
            admin.setRoleGroup(Users.ADMIN);
            admin.setEmail("admin@ace.com");
            admin.setMobile("0000 0000");
            admin.setGender(null);
            admin.setDateOfBirth(LocalDateTime.now());
            admin.setLoginDateTime(LocalDateTime.now());
            admin.setStatus(Users.ACTIVE);
            admin.setDomain("ace.com");
            admin.setIp("127.0.0.1");
            admin.setDomain("ace.com");
            admin.setRemark("ACE APPLICATION");
            admin.setEnabled(true);
            admin.setRecord(false);
            admin.setExpireDate(LocalDateTime.now().plusYears(100L));
            admin = usersService.saveAndFlush(admin);

            UserRoles adminUsersRoles = new UserRoles();
            adminUsersRoles.setRoleId(adminRoles.getRoleId());
            adminUsersRoles.setUserId(admin.getUserId());
            userRolesService.save(adminUsersRoles);
        }

        if (NullUtil.isNull(garlam)) {
            garlam = new Users();
            garlam.setPassword(passwordEncoder.encode("909394"));//password 909394
            garlam.setUserAccount("garlam");
            garlam.setUsername("garlam");
            garlam.setRoleGroup(Users.ADMIN);
            garlam.setEmail("garlam@ace.com");
            garlam.setMobile("9518 6540");
            garlam.setGender("M");
            garlam.setDateOfBirth(LocalDateTime.now());
            garlam.setLoginDateTime(LocalDateTime.now());
            garlam.setStatus(Users.ACTIVE);
            garlam.setDomain("ace.com");
            garlam.setIp("127.0.0.1");
            garlam.setDomain("ace.com");
            garlam.setRemark("ACE APPLICATION");
            garlam.setEnabled(true);
            garlam.setRecord(false);
            garlam.setExpireDate(LocalDateTime.now().plusYears(100L));
            garlam = usersService.saveAndFlush(garlam);

            UserRoles garlamUsersRoles = new UserRoles();
            garlamUsersRoles.setRoleId(adminRoles.getRoleId());
            garlamUsersRoles.setUserId(garlam.getUserId());
            userRolesService.save(garlamUsersRoles);

        }
        return RespResult.success("User: administrator and garlam has been generated into roles");
    }

    @Operation(summary = "删除所有角色和权限数据")
    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllRolePermission")
    public RespResult deleteAllRolePermission() {
        rolesService.deleteAll();
        permissionsService.deleteAll();
        return RespResult.success("Roles and Permission deleted !");
    }

    /**
     * 关联用户组别和权限级别
     *
     * @return AjaxResponse
     */
    @Operation(summary = "重建角色和权限关系")
    @RequestMapping(method = RequestMethod.GET, value = "/buildRolesPermissions")
    public RespResult buildRolesPermissions() {
        int rSize = rolesService.findAll().size();
        int pSize = permissionsService.findAll().size();
        if (0 == rSize && 0 == pSize) {
            log.info("Clean ROLES and PERMISSION DATA ...");
            rolePermissionsService.deleteAll();
            log.info("Clean ROLES and PERMISSION COMPLETED !");

            log.info("Build ROLES and PERMISSION data ...");
            rolesRestController.rebuildRoles();
            permissionRestController.insertPermission();
            log.info("Build ROLES and PERMISSION COMPLETE !");
        } else {
            log.info("Roles size: {}", rolesService.findAll().size());
            log.info("Permission size: {}", permissionsService.findAll().size());
            log.warn("请清空roles和permission数据 !");
            String c = "Rebuild roles permission fail !!! " + " Roles size: " + rSize + " Permission size: " + pSize;
            return RespResult.error(new ResponseException(c));
        }

        //insert default roles
        Roles roleAdmin = rolesService.findByRoleCode(Roles.ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Roles.USER);
        Roles RoleDisable = rolesService.findByRoleCode(Roles.DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Roles.VIEWER);

        RolePermissions all;
        RolePermissions insert;
        RolePermissions update;
        RolePermissions select;
        RolePermissions delete;
        RolePermissions deny;

        for (Roles roles : rolesService.findAll()) {
            Permissions permission;
            if (Users.ACTIVE.equals(roles.getStatus())) {
                switch (roles.getRoleCode()) {
                    case Roles.ADMIN:
                        all = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.ALL);
                        all.setPermissionsId(permission.getPermissionsId());
                        all.setRoleId(roleAdmin.getRoleId());
                        rolePermissionsService.save(all);
                        break;
                    case Roles.DISABLE:
                        deny = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.DENY);
                        deny.setPermissionsId(permission.getPermissionsId());
                        deny.setRoleId(RoleDisable.getRoleId());
                        rolePermissionsService.save(deny);
                        break;
                    case Roles.USER:
                        select = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleUser.getRoleId());

                        update = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.UPDATE);
                        update.setPermissionsId(permission.getPermissionsId());
                        update.setRoleId(roleUser.getRoleId());

                        insert = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.INSERT);
                        insert.setPermissionsId(permission.getPermissionsId());
                        insert.setRoleId(roleUser.getRoleId());

                        rolePermissionsService.save(select);
                        rolePermissionsService.save(update);
                        rolePermissionsService.save(insert);
                        break;
                    case Roles.VIEWER:
                        select = new RolePermissions();
                        permission = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
                        select.setPermissionsId(permission.getPermissionsId());
                        select.setRoleId(roleViewer.getRoleId());

                        rolePermissionsService.save(select);
                        break;
                }
            }
        }
        //   }
        return RespResult.success("角色和权限关系已重建");
    }


    /**
     * 整理没有用户组别的现有用户
     *
     * @return AjaxResponse
     */
    @Operation(summary = "重建现有用户的角色和权限, 默认用户除外")
    @RequestMapping(method = RequestMethod.GET, value = "/rebuildUsersRolesPermission")
    public RespResult rebuildUsersRolesPermissionRelation() {
        //find default users
        List<String> accounts = new ArrayList<>();
        accounts.add("admin");
        accounts.add("garlam");
        List<Users> nonDefaultUsers = usersService.findByUserAccountNotIn(accounts); // 找出默认用户除外的用户

        //find default user roles
        Users admin = usersService.findByUserAccount("admin");
        Users garlam = usersService.findByUserAccount("garlam");
        List<UserRoles> adminRoles = userRolesService.findAllByUserId(admin.getUserId());
        List<UserRoles> garlamRoles = userRolesService.findAllByUserId(garlam.getUserId());

        List<Long> defaultUserRolesIds = new ArrayList<>();
        defaultUserRolesIds.add(adminRoles.get(0).getUserRolesId());
        defaultUserRolesIds.add(garlamRoles.get(0).getUserRolesId());

        //find user roles list without default users
        List<UserRoles> userRolesWithoutDefaultUsers = userRolesService.findAllByUserRolesIdNotIn(defaultUserRolesIds);

        // delete user roles relation without default users
        userRolesService.deletes(userRolesWithoutDefaultUsers);

        Roles roleAdmin = rolesService.findByRoleCode(Roles.ADMIN);
        Roles roleUser = rolesService.findByRoleCode(Roles.USER);
        Roles RoleDisable = rolesService.findByRoleCode(Roles.DISABLE);
        Roles roleViewer = rolesService.findByRoleCode(Roles.VIEWER);

        List<UserRoles> userRolesList = new ArrayList<>();
        //用户加入角色
        for (Users nonDefaultUser : nonDefaultUsers) {
            UserRoles userRoles = new UserRoles();
            switch (nonDefaultUser.getRoleGroup()) {
                case Users.ADMIN:
                    userRoles.setUserId(nonDefaultUser.getUserId());
                    userRoles.setRoleId(roleAdmin.getRoleId());
                    break;
                case Users.DISABLE:
                    userRoles.setUserId(nonDefaultUser.getUserId());
                    userRoles.setRoleId(RoleDisable.getRoleId());
                    break;
                case Users.USER:
                    userRoles.setUserId(nonDefaultUser.getUserId());
                    userRoles.setRoleId(roleUser.getRoleId());
                    break;
                case Users.VIEWER:
                    userRoles.setUserId(nonDefaultUser.getUserId());
                    userRoles.setRoleId(roleViewer.getRoleId());
                    break;
            }
            userRolesList.add(userRoles);
        }
        userRolesService.saveAll(userRolesList); //保存到user_roles

        rolePermissionsService.deleteAll(); // delete all role_permissions relation

        List<Roles> rolesList = rolesService.findAll();
        int rolesSize = rolesList.size();
        Permissions p0 = permissionsService.findPermissionsByPermissionCode(Permissions.ALL);
        Permissions p1 = permissionsService.findPermissionsByPermissionCode(Permissions.INSERT);
        Permissions p2 = permissionsService.findPermissionsByPermissionCode(Permissions.UPDATE);
        Permissions p3 = permissionsService.findPermissionsByPermissionCode(Permissions.DELETE);
        Permissions p4 = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);
        Permissions p10 = permissionsService.findPermissionsByPermissionCode(Permissions.DENY);

        //保存到role_permissions
        for (int i = 0; i < rolesSize; i++) {
            RolePermissions all;
            RolePermissions insert;
            RolePermissions update;
            RolePermissions delete;
            RolePermissions select;
            RolePermissions deny;

            switch (rolesList.get(i).getRoleCode()) {
                case Roles.ADMIN:
                    all = new RolePermissions();
                    all.setRoleId(rolesList.get(i).getRoleId());
                    all.setPermissionsId(p0.getPermissionsId());

                    rolePermissionsService.save(all);
                    break;
                case Roles.DISABLE:
                    deny = new RolePermissions();
                    deny.setRoleId(rolesList.get(i).getRoleId());
                    deny.setPermissionsId(p10.getPermissionsId());

                    rolePermissionsService.save(deny);
                    break;
                case Roles.USER:
                    insert = new RolePermissions();
                    insert.setRoleId(rolesList.get(i).getRoleId());
                    insert.setPermissionsId(p1.getPermissionsId());

                    update = new RolePermissions();
                    update.setRoleId(rolesList.get(i).getRoleId());
                    update.setPermissionsId(p2.getPermissionsId());

                    select = new RolePermissions();
                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(insert);
                    rolePermissionsService.save(update);
                    rolePermissionsService.save(select);
                    break;
                case Roles.VIEWER:
                    select = new RolePermissions();
                    select.setRoleId(rolesList.get(i).getRoleId());
                    select.setPermissionsId(p4.getPermissionsId());

                    rolePermissionsService.save(select);
                    break;
            }
        }
        return RespResult.success("除去默认用户, 其他用户已重新建立角色和权限关系");
    }



    @Operation(summary = "查询用户的角色权限关系,java实现")
    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermissionByUserAccount/{userAccount}")
    public RespResult findUserRolePermissionByUserAccount(@PathVariable String userAccount) {
        log.info("user {} 的角色和权限", userAccount);
        Users users = usersService.findByUserAccount(userAccount);
        List<Roles> rolesList = rolesService.getRolesByUserId(users.getUserId());
        List<Permissions> permissions = rolePermissionsService.findPermissionsInRoleCode(rolesList);

        Map<String, Map> r = new HashMap<>();
        for (Roles rs : rolesList) {
            Map p = new HashMap();
            for (Permissions ps : permissions) {
                p.put("权限代码:"+ps.getPermissionCode() , ps.getAction());
            }
            r.put(rs.getRoleCode(), p);
        }
        Map<String, Map<String, Map>> urp = new HashMap<>();
        urp.put(users.getUserAccount(), r);
        return RespResult.success(urp);
    }


    @Operation(summary = "查询用户的角色权限关系,sql实现")
    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermissionByUserAcc/{userAccount}")
    public RespResult findUserRolePermissionByUserAcc(@PathVariable String userAccount) {
        log.info("user {} 的角色和权限", userAccount);

        List<Map> list = usersService.findUserRolePermissionByUserAccount(userAccount);
        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeySet(map);
        }
        return RespResult.success(list);
    }

    @Operation(summary = "查询所有用户的角色权限关系,sql实现")
    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermission")
    public RespResult findUserRolePermission() {
        log.info("List detail by USERS对像");
        List<Map> list = usersService.findUserRolePermission();
        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeySet(map);
        }
        return RespResult.success(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findUserRolePermissionDetail")
    public RespResult findUserRolePermissionDetail() {
        log.info("List detail by UserRolePermission");
        List<Map> list = usersService.findUserRolePermissionDetail();
        MapUtil mapUtil = new MapUtil();
        for (Map map : list) {
            System.out.println("--------------");
            mapUtil.iterateMapKeySet(map);
        }
        return RespResult.success(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserRolePermissionById/{userId}")
    public RespResult getUserRolePermissionById(@PathVariable String userId) {
        log.info("List detail by UserRolePermission Mybatis");
        Long id = Long.valueOf(userId);
        List<Map> m0 = usersService.findAllUserRolePermissionByMybatis();
        List<Map> getUsersByMybatis = usersService.getUserRolePermissionById(id);
        List<Map> getUsersByHibernate = usersService.findUserRolePermissionDetailById(id);

        Long userIdMybatis = (Long) getUsersByMybatis.get(0).get("userId");
        String userAccount = (String) getUsersByMybatis.get(0).get("userAccount");
        String roleName = (String) getUsersByMybatis.get(0).get("roleName");
        String permissionCode = (String) getUsersByMybatis.get(0).get("permissionCode");
        Long permissionsId = (Long) getUsersByMybatis.get(0).get("permissionsId");
        log.info("userId form Mybatis: {}", userIdMybatis);
        log.info("userAccount: {}", userAccount);
        log.info("roleName: {}", roleName);
        log.info("permissionCode: {}", permissionCode);
        log.info("permissionsId: {}", permissionsId);

        List<List<Map>> result = new ArrayList<>();
        Map mybatisMap = new HashMap();
        mybatisMap.put("source 1", "mybatis");
        Map hibernateMap = new HashMap();
        hibernateMap.put("source 2", "hibernate");

        List<Map> result1 = new ArrayList<>();
        result1.add(mybatisMap);
        List<Map> result2 = new ArrayList<>();
        result2.add(hibernateMap);


        result.add(result1);
        result.add(getUsersByMybatis);
        result.add(result2);
        result.add(getUsersByHibernate);

        return RespResult.success(result);
    }

    @Operation(summary = "查詢用户的角色和权根关系")
    @RequestMapping(method = RequestMethod.GET, value = "/getByUserAccount/{userAccount}")
    public RespResult getUserRolePermissionByUserAccount(@PathVariable String userAccount) {
        log.info("List detail by UserRolePermission Mybatis");
        Users user = usersService.findByUserAccount(userAccount);
        List<Map> getUsersByHibernate = usersService.findUserRolePermissionDetailById(user.getUserId());

        List results = new ArrayList();
        for (Map map : getUsersByHibernate) {
            Map m = new LinkedHashMap();
            m.put("userId", map.get("userId"));
            m.put("userAccount", map.get("userAccount"));
            m.put("roleCode", map.get("roleCode"));
            m.put("action", map.get("action"));
            m.put("status", map.get("status"));
            m.put("roleId", map.get("roleId"));
            m.put("roleName", map.get("roleName"));
            m.put("permissionsId", map.get("permissionsId"));
            m.put("permissionCode", map.get("permissionCode"));
            m.put("ip", map.get("ip"));
            results.add(m);
        }
        return RespResult.success(results);
    }
}

