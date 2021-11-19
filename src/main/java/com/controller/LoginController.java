package com.controller;

import com.controller.common.CommonController;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: LoginController
 * @Date: 1/7/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class LoginController extends CommonController {
	private static Logger log = LogManager.getLogger(LoginController.class.getName());

	private UsersService usersService;

	@Autowired
	public LoginController(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView modelAndView = super.page("ace/login.html");
		return modelAndView;
	}


	@RequestMapping(value = "/logging.html", method = RequestMethod.POST)
	public ModelAndView login(String userAccount, String password, HttpServletRequest request) {
		log.info("userAccount: " + userAccount);
		log.info("password: " + password);

		String userMsg = request.getParameter("msg");
		ModelAndView modelAndView;

		//用户存在
		if ("exist".equals(userMsg)) {
			modelAndView = super.page("ace/login.html");
			modelAndView.addObject("msg", "User already exist !");
			return modelAndView;
		}
		String msg;

		Users user = new Users();
		if (NullUtil.isNotNull(userAccount) && NullUtil.isNotNull(password)) {
			user.setUserAccount(userAccount);
			user.setPassword(password);
			user = usersService.getUserByUserName(user);
		} else if (NullUtil.isNull(userAccount) && NullUtil.isNull(password)) {
			modelAndView = super.page("ace/login.html");
			return modelAndView;
		} else {
			log.error("check input param fail!");
			modelAndView = super.page("ace/login.html");
			modelAndView.addObject("msg", "UserName/password is empty!");
			return modelAndView;
		}

		if (NullUtil.isNotNull(user.getUserId())) {
			String mobile = user.getMobile();
			Integer newMobile = DataTypeUtil.stringToInteger(mobile == null ? "1" : mobile) + 1;
			mobile = newMobile.toString();
			user.setMobile(mobile);
			usersService.save(user);
			log.info("user: " + user.getUserName() + " save success!");
			modelAndView = super.redirect("ace/index.html");
		} else {
			log.error("Login Fail!");
			modelAndView = super.page("ace/login.html");
			if (userAccount.isEmpty() || password.isEmpty()) {
				msg = "Account / password empty";
			} else {
				msg = "Login Fail, Please try again!";
			}
			modelAndView.addObject("msg", msg);
			//modelAndView.addObject("userAccount", userAccount);
		}
		return modelAndView;
	}
}

