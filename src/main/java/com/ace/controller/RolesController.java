package com.ace.controller;


import com.ace.controller.common.CommonController;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.RolePermissions;
import com.ace.service.PermissionsService;
import com.ace.service.RolePermissionsService;
import com.google.gson.Gson;
import com.ace.models.entity.Roles;
import com.ace.service.RolesService;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.util.NullUtil;

import java.util.ArrayList;
import java.util.List;


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
        return rolesService.getRolesByUserId(userId);
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

  //  @RequestMapping(value = "/roles/update.html", method = RequestMethod.GET)
   // @ResponseBody
    public ModelAndView updatePermissionByRoleId(@RequestParam(value = "roleId") Long roleId, @RequestParam(value = "actions") List<String> actions) {
        log.info("roleId: {}, permissionId: {}", roleId, actions);
//        log.info("roleId: {}", roleId);


        //  rolePermissionsService.deleteByRoleId(roleId);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");

//        try {
//            List<RolePermissions> rpList = new ArrayList<>();
//            for (String action : actions) {
//                Permissions p = permissionsService.findPermissionsByAction(action);
//                RolePermissions rp = new RolePermissions();
//                rp.setRoleId(roleId);
//                rp.setPermissionsId(p.getPermissionsId());
//                rpList.add(rp);
//            }
//            rolePermissionsService.saveAll(rpList);
//            modelAndView.addObject("ajaxResult", actions);
//        } catch (Exception e) {
//            e.printStackTrace();
//            modelAndView.addObject("ajaxResult", false);
//        }

        return modelAndView;
    }

}
