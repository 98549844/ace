package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.exception.ResponseException;
import com.ace.generator.insertRoles;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.Roles;
import com.ace.models.entity.Users;
import com.ace.service.RolePermissionsService;
import com.ace.service.RolesService;
import com.ace.service.UserRolesService;
import com.ace.service.UsersService;
import com.util.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @Classname: RolesRestController
 * @Date: 12/12/2021 5:54 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/roles")
//@Api(tags = "roles")
@Tag(name = "Roles")
public class RolesRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(RolesRestController.class.getName());

    private final UsersService usersService;
    private final RolesService rolesService;
    private final RolePermissionsService rolePermissionsService;


    @Autowired
    public RolesRestController(RolesService rolesService, UsersService usersService, RolePermissionsService rolePermissionsService) {
        this.rolesService = rolesService;
        this.usersService = usersService;
        this.rolePermissionsService = rolePermissionsService;
    }


    @Operation(summary = "重建角色", description = "删除所有角色并重建")
    @RequestMapping(method = RequestMethod.GET, value = "/rebuildRoles")
    public AjaxResponse rebuildRoles() {
        Users user = usersService.findByUserAccount("garlam");
        rolesService.deleteAll();
        log.info("All roles DELETED !");

        //generate roles data
        insertRoles insertRoles = new insertRoles();
        List<Roles> ls = insertRoles.insertRoles(user);

        rolesService.saveAllAndFlush(ls);
        log.info("Default roles has been CREATED !");

        Map<Long, String> map = new LinkedHashMap();
        for (Roles role : ls) {
            map.put(role.getRoleId(), role.getRoleCode());
        }
        return AjaxResponse.success(map);
    }

    @Operation(summary = "查詢所有角色")
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public AjaxResponse getRoles() {
        List<Roles> ls = rolesService.findAll();
        List<String> result = new ArrayList<>();
        for (Roles roles : ls) {
            String u = "   [" + roles.getRoleCode() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllRole")
    public AjaxResponse deleteAllRole() {
        rolesService.deleteAll();
        return AjaxResponse.success("All roles deleted");
    }

    @Operation(summary = "根据roleCode查所属权限")
    @RequestMapping(method = RequestMethod.GET, value = "/getPermissionByRoleCode/{roleCode}")
    public AjaxResponse getPermissionByRoleCode(@PathVariable String roleCode) {
        roleCode = roleCode.toUpperCase();
        List<Permissions> permissionsList = rolePermissionsService.findPermissionsByRoleCode(roleCode);
        List<Map> result = new ArrayList<>();
        for (Permissions p : permissionsList) {
            Map ps = new LinkedHashMap();
            ps.put("Permissions Id", p.getPermissionsId());
            ps.put("Action", p.getAction());
            ps.put("Permission Code", p.getPermissionCode());
            ps.put("description", p.getDescription());
            result.add(ps);
        }
        return AjaxResponse.success(result);
    }


    @Operation(summary = "查询角色状态", description = "ACTIVE / INACTIVE")
    @RequestMapping(method = RequestMethod.GET, value = "/status/{roleCode}")
    public AjaxResponse setStatusByRoleCode(@NotNull @PathVariable String roleCode) {
        Roles roles = rolesService.findByRoleCode(roleCode);
        if(NullUtil.isNull(roles)){
            return AjaxResponse.error(new ResponseException("找不到 "+roleCode+" 资料"));
        }
        Map map = new HashMap();
        map.put("Role Code", roles.getRoleCode());
        map.put("status", roles.getStatus());
        return AjaxResponse.success(map);
    }

    @Operation(summary = "控制角色开关", description = "ACTIVE / INACTIVE")
    @RequestMapping(method = RequestMethod.GET, value = "/status/{roleCode}/{status}")
    public AjaxResponse setStatusByRoleCode(@NotNull @PathVariable String roleCode, @NotNull @PathVariable String status) {
        Roles roles = rolesService.findByRoleCode(roleCode);
        status = status.toUpperCase();
        if (Roles.ACTIVE.equals(status) || Roles.INACTIVE.equals(status)) {
            roles.setStatus(status.toUpperCase());
            roles = rolesService.saveAndFlush(roles);
        } else {
            return AjaxResponse.error(new ResponseException("status code 不正确, 请输入 ACTIVE / INACTIVE"));
        }
        Map map = new HashMap();
        map.put("Role Code", roles.getRoleCode());
        map.put("status", roles.getStatus());

        return AjaxResponse.success(map);
    }
}

