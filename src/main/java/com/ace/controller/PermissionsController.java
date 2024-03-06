package com.ace.controller;


import com.ace.controller.common.CommonController;
import com.ace.models.entity.Permissions;
import com.ace.service.PermissionsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class PermissionsController extends CommonController {
    private static final Logger log = LogManager.getLogger(PermissionsController.class.getName());

    private final PermissionsService permissionsService;

    public PermissionsController(PermissionsService permissionsService) {
        this.permissionsService = permissionsService;
    }

    @RequestMapping(value = "/permissions.html", method = RequestMethod.GET)
    public ModelAndView permissions() {
        ModelAndView modelAndView = super.page("ace/modules/permissions/permissions");
        return modelAndView;
    }

    @RequestMapping(value = "/getAll.html", method = RequestMethod.GET)
    @ResponseBody
    public List<Permissions> getAll() {
        return permissionsService.findAll();
    }

}
