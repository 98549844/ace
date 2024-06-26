package com.ace.controller;

import com.ace.controller.common.CommonController;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.RolePermissions;
import com.ace.models.entity.Roles;
import com.ace.service.RolePermissionsService;
import com.ace.service.RolesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @Classname: RolePermissionController
 * @Date: 3/1/2022 1:41 AM
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class RolePermissionController extends CommonController {
    private static final Logger log = LogManager.getLogger(RolePermissionController.class.getName());

    private final RolesService rolesService;
    private final RolePermissionsService rolePermissionsService;

    public RolePermissionController(RolesService rolesService, RolePermissionsService rolePermissionsService) {
        this.rolesService = rolesService;
        this.rolePermissionsService = rolePermissionsService;
    }

    @RequestMapping(value = "/rolePermissions/updateRolePermission.html", method = RequestMethod.POST)
    public ModelAndView registration(Long rolesId, List<Permissions> permissions) {
        log.info("清空角色权限并重建角色权限关系: {}", permissions);
        ModelAndView modelAndView = new ModelAndView();
        Roles roles = rolesService.findRolesByRoleId(rolesId);
        //delete roles permission
        List<RolePermissions> rolePermissions = rolePermissionsService.findRolePermissionsByRoleId(rolesId);
        for (RolePermissions r : rolePermissions) {
            rolePermissionsService.delete(r);
        }

        //insert roles permission
        for (Permissions p : permissions) {
            RolePermissions rolePermission = new RolePermissions();
            rolePermission.setRoleId(roles.getRoleId());
            rolePermission.setPermissionsId(p.getPermissionsId());
            rolePermissionsService.save(rolePermission);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/rolePermissions/deleteAllByRoleId.html/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteAllByRoleId(@PathVariable Long roleId) {
        rolePermissionsService.deleteByRoleId(roleId);
        return true;
    }


}

