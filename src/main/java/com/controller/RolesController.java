package com.controller;


import com.constant.Css;
import com.controller.common.CommonController;
import com.models.entity.dao.Roles;
import com.models.entity.dao.Users;
import com.service.RolesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.NullUtil;

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
    private static Logger log = LogManager.getLogger(RolesController.class.getName());

    private RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @RequestMapping(value = "/roles.html", method = RequestMethod.GET)
    public ModelAndView getRoleList() {
        List<Roles> rolesList = rolesService.findAll();
        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);
        //submenu css control
        //  modelAndView.addObject(Css.open, Css.open);
        //  modelAndView.addObject("widgetBox", "widget-box collapsed");
        //  modelAndView.addObject("faChevron", "ace-icon fa fa-chevron-down");
        return modelAndView;
    }

    @RequestMapping(value = "/roles/create.html", method = RequestMethod.GET)
    public ModelAndView insertRole(Long rolesId, String roleName, String roleCode, String select, String update, String insert, String delete) {
        log.info("roleName: {}", roleName);
        log.info("roleCode: {}", roleCode);
        log.info("select: {}", select);
        log.info("update: {}", update);
        log.info("insert: {}", insert);
        log.info("delete: {}", delete);

        Roles role = rolesService.findByRoleCode(roleCode);
        //  Roles role = rolesService.findRolesByRoleId(rolesId);

        if (NullUtil.isNotNull(role)) {
            // return ajax show artdialog
        }

        List<Roles> rolesList = rolesService.findAll();
        ModelAndView modelAndView = super.page("ace/modules/roles/roles");
        modelAndView.addObject("roles", rolesList);
        //submenu css control
        //modelAndView.addObject(Css.open, Css.open);
        //modelAndView.addObject("widgetBox", "widget-box");
        //modelAndView.addObject("faChevron", "ace-icon fa fa-chevron-up");

        return modelAndView;
    }
}
