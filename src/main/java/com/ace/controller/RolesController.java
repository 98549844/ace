package com.ace.controller;


import com.ace.controller.common.CommonController;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.RolePermissions;
import com.ace.models.entity.Roles;
import com.ace.service.PermissionsService;
import com.ace.service.RolePermissionsService;
import com.ace.service.RolesService;
import com.ace.utilities.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RolesController extends CommonController {
    private static final Logger log = LogManager.getLogger(RolesController.class.getName());

    private final RolesService rolesService;
    private final RolePermissionsService rolePermissionsService;
    private final PermissionsService permissionsService;

    @Autowired
    public RolesController(RolesService rolesService, RolePermissionsService rolePermissionsService, PermissionsService permissionsService) {
        this.rolesService = rolesService;
        this.rolePermissionsService = rolePermissionsService;
        this.permissionsService = permissionsService;
    }

    @RequestMapping(value = "/roles/getByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Roles> getRolesByUserId(@PathVariable Long userId) {
        log.info("access roles/getByUserId/{}", userId);
        List<Roles> roles = rolesService.getRolesByUserId(userId);
        return roles;
    }


    @RequestMapping(value = "/roles.html", method = RequestMethod.GET)
    public ModelAndView getRoleList() {
        List<Roles> rolesList = rolesService.findAll();
        for (int i = 0; i < rolesList.size(); i++) {
            Roles roles = rolesList.get(i);
            List<Permissions> permissions = rolePermissionsService.findPermissionsByRoleCode(roles.getRoleCode());
            rolesList.get(i).setPermissions(permissions);
        }


        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);
        return modelAndView;
    }

    @RequestMapping(value = "/roles/create.html", method = RequestMethod.GET)
    public ModelAndView insertRole(String roleName, String roleCode, String select, String update, String insert, String delete) {
        log.info("roleName: {}", roleName);
        log.info("roleCode: {}", roleCode);
        log.info("select: {}", select);
        log.info("update: {}", update);
        log.info("insert: {}", insert);
        log.info("delete: {}", delete);

        List<Roles> rolesList = rolesService.findAll();
        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);

        return modelAndView;
    }

    @RequestMapping(value = "/roles/check.html", method = RequestMethod.GET)
    public ModelAndView checkRole(String roleCode) {
        log.info("roleCode: {}", roleCode);
        Roles roles = rolesService.findByRoleCode(roleCode);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        if (NullUtil.isNonNull(roles)) {
            log.info("exist");
            modelAndView.addObject("ajaxResult", "exist");
        } else {
            log.info("not exist");
            modelAndView.addObject("ajaxResult", "not exist");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/roles/getPermission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Roles getPermissionByRoleCode(@PathVariable Long roleId) {
        log.info("roleCode: {}", roleId);
        Roles role = rolesService.findRolesByRoleId(roleId);
        List<Permissions> ps = rolePermissionsService.findPermissionsByRoleCode(role.getRoleCode());
        role.setPermissions(ps);
        return role;
    }

    @RequestMapping(value = "/roles/update.html/{roleId}/{action}", method = RequestMethod.GET)
    @ResponseBody
    public Map updatePermissionByRoleId(@PathVariable Long roleId, @PathVariable(value = "action") String action) {
        log.info("roleId: {}, action: {}", roleId, action);
        Permissions p = permissionsService.findPermissionsByAction(action);
        Long pId = p.getPermissionsId();
        RolePermissions rp = rolePermissionsService.findRolePermissionsByRoleIdAndPermissionsId(roleId, pId);
        Map map = new HashMap();
        if (NullUtil.isNull(rp)) {
            //增加角色权限
            RolePermissions rolePermissions = new RolePermissions();
            rolePermissions.setPermissionsId(pId);
            rolePermissions.setRoleId(roleId);
            rolePermissionsService.save(rolePermissions);
            map.put("result", true);
            map.put("pCode", p.getPermissionCode());

        } else {
            //删除角色权限
            rolePermissionsService.delete(rp);
            map.put("result", false);
            map.put("pCode", null);
        }
        return map;
    }

}
