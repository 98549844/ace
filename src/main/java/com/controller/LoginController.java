package com.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.controller.common.CommonController;
import com.exception.PasswordNotMatchException;
import com.exception.UserNotFoundException;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.NullUtil;

import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView login(HttpServletRequest request) {
		log.info("LoginController.login(): " );
		ModelAndView modelAndView = super.page("ace/login.html");
		String userMsg = request.getParameter("msg");
		//用户存在
		if ("exist".equals(userMsg)) {
			modelAndView.addObject("msg", "User exist");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/logging.html", method = RequestMethod.POST)
	public ModelAndView logging(String userAccount, String password, HttpServletRequest request) {
		log.info("userAccount: " + userAccount);
		log.info("password: " + password);

		ModelAndView modelAndView;
		String msg;
		Users user = new Users();
		if (userAccount.isEmpty() || password.isEmpty()) {
			//check input param
			log.error("Account/Password empty");
			msg = "Account/Password empty";
			modelAndView = super.page("ace/login.html");
			modelAndView.addObject("msg", msg);
			return modelAndView;
		} else if (NullUtil.isNotNull(userAccount) && NullUtil.isNotNull(password)) {
			user.setUserAccount(userAccount);
			user.setPassword(password);
			try {
				//get user information
				user = usersService.findByUserAccount(user);
			} catch (UserNotFoundException | PasswordNotMatchException e) {
				e.printStackTrace();
				modelAndView = super.page("ace/login.html");
				msg = "Login Fail, Please try again!";
				modelAndView.addObject("msg", msg);
				return modelAndView;
			}
		}
		log.info("user: " + user.getUsername() + " save success!");
		modelAndView = super.redirect("ace/index.html");
		StpUtil.login(user.getUserId());
		return modelAndView;
	}
}

