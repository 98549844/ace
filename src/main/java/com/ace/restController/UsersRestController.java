package com.ace.restController;

import com.ace.aspectj.AceLog;
import com.ace.controller.common.CommonController;
import com.ace.exception.ResponseException;
import com.ace.generator.InsertUsers;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Roles;
import com.ace.models.entity.UserRoles;
import com.ace.models.entity.Users;
import com.ace.service.RolesService;
import com.ace.service.UserRolesService;
import com.ace.service.UsersService;
import com.ace.utilities.ListUtil;
import com.ace.utilities.NullUtil;
import com.ace.utilities.RandomUtil;
import com.ace.utilities.TypeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname: UserRestController
 * @Date: 5/7/2021 10:49 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/users")
@Tag(name = "Users")
public class UsersRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(UsersRestController.class.getName());

    private final UsersService usersService;
    private final UserRolesService userRolesService;
    private final PasswordEncoder passwordEncoder;
    private final RolesService rolesService;
    private final UserRolePermissionRestController userRolePermissionRestController;

    @Autowired
    public UsersRestController(UserRolesService userRolesService, UserRolePermissionRestController userRolePermissionRestController, UsersService usersService, PasswordEncoder passwordEncoder, RolesService rolesService) {
        this.usersService = usersService;
        this.userRolesService = userRolesService;
        this.passwordEncoder = passwordEncoder;
        this.userRolePermissionRestController = userRolePermissionRestController;
        this.rolesService = rolesService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addDefaultUsers")
    public AjaxResponse addDefaultAdminUsers() {
        return userRolePermissionRestController.addDefaultAdminUsers();
    }


    @Operation(summary = "查询用户")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{userAccount}")
    public AjaxResponse getUserByUserAccount(@PathVariable String userAccount) {
        Users user = usersService.findByUserAccount(userAccount);
        return AjaxResponse.success(user);
    }

    @Operation(summary = "查询用户角色")
    @RequestMapping(method = RequestMethod.GET, value = "/getRoles/{userAccount}")
    public AjaxResponse getUserRoleByUserAccount(@PathVariable String userAccount) {
        Users user = usersService.findByUserAccount(userAccount);
        List<Roles> rolesList = rolesService.getRolesByUserId(user.getUserId());
        List<String> r = new ArrayList<>();
        for (Roles rs : rolesList) {
            r.add(rs.getRoleCode());
        }
        Map<String, List> urp = new HashMap<>();
        urp.put(user.getUserAccount(), r);
        return AjaxResponse.success(urp);
    }

    @Operation(summary = "根据userAccount更新角色组", description = "清空原有用户的角色并更新, List<String> = xxx,xxx")
    @RequestMapping(method = RequestMethod.GET, value = "/updateRoles/{userAccount}/{rolesCode}")
    public AjaxResponse updateUserRoles(@PathVariable String userAccount, @NotNull @PathVariable List<String> rolesCode) {
        List<Roles> rolesList = rolesService.findRolesByRoleCodeIn(ListUtil.convertToUpperCase(rolesCode));
        if (rolesList.isEmpty()) {
            log.warn(rolesCode + " 查询不到结果");
            return AjaxResponse.error(new ResponseException("查无相关角色: " + rolesCode));
        }
        Users user = usersService.findByUserAccount(userAccount);
        //删除用户和角色关系
        userRolesService.deleteUserRolesByUserId(user.getUserId());

        Map map = new HashMap();
        for (Roles r : rolesList) {
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(user.getUserId());
            userRoles.setRoleId(r.getRoleId());
            userRoles = userRolesService.saveAndFlush(userRoles);
            map.put(userRoles.getUserRolesId(), user.getUserAccount() + " => " + r.getRoleCode());
        }
        return AjaxResponse.success(map);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get/{userId}")
    public AjaxResponse getUserById(@PathVariable Long userId) {
        Users user = usersService.findUsersById(userId);
        return AjaxResponse.success(user);
    }

    @Operation(summary = "删除用户角色")
    @RequestMapping(method = RequestMethod.POST, value = "/deleteBy/{userAccount}/{roleCode}")
    public AjaxResponse deleteByUserAccount(@PathVariable String userAccount, @PathVariable String roleCode) {
        roleCode = roleCode.toUpperCase();
        log.info("userAccount: {}  roleCode: {}", userAccount, roleCode);
        try {
            Users user = usersService.findByUserAccount(userAccount);
            Roles roles = rolesService.findByRoleCode(roleCode);
            Long userId = user.getUserId();
            Long roleId = roles.getRoleId();
            if (NullUtil.isNull(userRolesService.findUserRolesByUserIdAndRoleId(userId, roleId))) {
                userRolesService.deleteUserRolesByUserIdAndRoleId(user.getUserId(), roles.getRoleId());
            }
            return AjaxResponse.success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResponse.error(new ResponseException("operation fail !"));
        }
    }

    @Operation(summary = "新增用户角色")
    @RequestMapping(method = RequestMethod.POST, value = "/addBy/{userAccount}/{roleCode}")
    public AjaxResponse addByUserAccount(@PathVariable String userAccount, @PathVariable String roleCode) {
        roleCode = roleCode.toUpperCase();
        log.info("userAccount: {}  roleCode: {}", userAccount, roleCode);
        try {
            Users user = usersService.findByUserAccount(userAccount);
            Roles roles = rolesService.findByRoleCode(roleCode);
            Long userId = user.getUserId();
            Long roleId = roles.getRoleId();
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(user.getUserId());
            userRoles.setRoleId(roles.getRoleId());
            if (NullUtil.isNull(userRolesService.findUserRolesByUserIdAndRoleId(userId, roleId))) {
                userRolesService.save(userRoles);
                return AjaxResponse.success(true);
            }
            return AjaxResponse.error(new ResponseException("operation fail !"));

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResponse.error(new ResponseException("user has " + roleCode + " this role."));
        }
    }

    @AceLog("Ace Aspectj") //自定义aspect
    @Operation(summary = "查询所有用户")
    @RequestMapping(method = RequestMethod.GET, value = "/getUsers")
    public AjaxResponse getUsers() {
        List<Users> ls = usersService.findAll();
        Map<Long, String> map = new HashMap<>();
        for (Users user : ls) {
            map.put(user.getUserId(), user.getUserAccount());
        }
        return AjaxResponse.success(map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsersByMybatis")
    public AjaxResponse getUsersByMybatis() {
        List<Users> ls = usersService.findAllByMybatis();
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsersLikeNameByMybatis/{username}")
    public AjaxResponse getUsersLikeNameByMybatis(@PathVariable String username) {
        List<Users> ls = usersService.findUsersLikeNameByMybatis(username);
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }


    /**
     * select statement 需要用jpa夹住mybatis update, 共用col才会update
     *
     * @param acc
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/updateUserByMybatis/{acc}")
    public AjaxResponse updateUserByMybatis(@PathVariable String acc) {

        Users users = usersService.findUserByMybatis(acc);
        log.info("before version: " + users.getVersion());

        users.setIp(getRequest().getRemoteAddr());
        users.setHostName(getRequest().getRemoteHost());
        users.setMobile(TypeUtil.integerToString(RandomUtil.getRangeInt(0, 99999999)));
        usersService.updateByMybatis(users);

        //users = usersService.findByUserAccount(acc);
        users = usersService.findUserByMybatis(acc);

        log.info("after version: " + users.getVersion());
        log.info("COMPLETE !!!");

        return AjaxResponse.success(users);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllUser")
    public AjaxResponse deleteAllUser() {
        usersService.deleteAll();
        userRolesService.deleteAll();
        return AjaxResponse.success("All users and roles relation deleted");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/deleteUsersWithoutDefaultUsers")
    public AjaxResponse deleteUsersWithoutDefaultUsers() {
        List<String> defaultUserAccounts = new ArrayList<>();
        defaultUserAccounts.add("admin");
        defaultUserAccounts.add("garlam");

        List<Users> users = usersService.findByUserAccountNotIn(defaultUserAccounts);

        List<Long> deleteUsers = new ArrayList<>();
        for (Users user : users) {
            deleteUsers.add(user.getUserId());
        }
        List<UserRoles> userRoles = userRolesService.findAllByUserIdIn(deleteUsers);

        usersService.deleteAll(users);
        userRolesService.deleteAll(userRoles);

        return AjaxResponse.success("All users and roles relation deleted, but not include default user account");
    }


    @Operation(summary = "sample用户生成")
    @RequestMapping(method = RequestMethod.GET, value = "/genSampleUser")
    public AjaxResponse genSampleUser(boolean remap) {
        //generate users data
        //default password = 909394
        String password = passwordEncoder.encode("909394");
        log.info("passwordEncoder matches=>" + passwordEncoder.matches("909394", password));
        List<Users> usersList = InsertUsers.insertUsers();
        usersService.saveAll(usersList);

        if (remap) {
            userRolePermissionRestController.rebuildUsersRolesPermissionRelation();
        }
        List<String> result = new ArrayList<>();
        for (Users user : usersList) {
            String u = user.getUsername() + "   [ 909394 ]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/reEncodePassword")
    public AjaxResponse reEncodePassword(@RequestParam("password") String password) {
        //get all user
        log.info("password: {}", password);
        List<Users> ls = usersService.findAll();

        List<Users> usersWithReEncodePassword = new ArrayList<>();
        for (Users users : ls) {
            String encode = passwordEncoder.encode(password);
            users.setPassword(encode);
            usersWithReEncodePassword.add(users);
        }
        usersService.saveAll(usersWithReEncodePassword);
        return AjaxResponse.success("All password re-encoded");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validatePassword/{password}")
    public AjaxResponse validatePassword(@PathVariable String password) {
        log.info("password: {}", password);
        //get all user
        List<Users> ls = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users users : ls) {
            boolean match = passwordEncoder.matches(password, users.getPassword());
            log.info("encode: {}", match);
            result.add(users.getUsername() + "[ " + match + " ]");
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/enable/{userAccount}/{enable}")
    public AjaxResponse setEnableByUserAccount(@NotNull @PathVariable String userAccount, @PathVariable boolean enable) {
        Users user = usersService.findByUserAccount(userAccount);
        user.setEnabled(enable);

        Map m = new HashMap();
        m.put("userAccount", user.getUserAccount());
        m.put("enable", user.isEnabled());
        m.put("roles RoleGroup", user.getRoleGroup());

        return AjaxResponse.success(m);
    }

}

