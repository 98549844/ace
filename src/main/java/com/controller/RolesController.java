package com.controller;


import com.controller.common.CommonController;
import com.models.entity.dao.Roles;
import com.service.RolesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		modelAndView.addObject("roles",rolesList);


		return modelAndView;
	}
}
