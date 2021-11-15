package com.controller;


import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class RolesController extends CommonController {
	private static Logger log = LogManager.getLogger(RolesController.class.getName());

	@RequestMapping(value = "/roles.html", method = RequestMethod.GET)
	public ModelAndView roles() {
		ModelAndView modelAndView = super.page("ace/modules/roles/roles");
		return modelAndView;
	}
}