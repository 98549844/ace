package com.controller;


import com.controller.common.CommonController;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class UserController extends CommonController {
	private static Logger log = LogManager.getLogger(UserController.class.getName());

	private UsersService usersService;

	@Autowired
	public UserController(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value = "/users.html", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		ModelAndView modelAndView = super.page("ace/modules/users/users");
		return modelAndView;
	}

}
