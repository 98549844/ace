package com.restController;

import com.models.common.AjaxResponse;
import com.models.entity.dao.Users;
import com.sampleDataGenerator.DataGenerator;
import com.sampleDataGenerator.insertUsers;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: UserRestController
 * @Date: 5/7/2021 10:49 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/user")
@Api(tags = "用户组")
@EnableConfigurationProperties
public class UsersRestController {
	private static Logger log = LogManager.getLogger(UsersRestController.class.getName());


	private UsersService usersService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UsersRestController(UsersService usersService, PasswordEncoder passwordEncoder) {
		this.usersService = usersService;
		this.passwordEncoder = passwordEncoder;
	}


	@RequestMapping(method = RequestMethod.GET, value = "/get")
	public boolean getUsers() {
		List<Users> ls = usersService.getAll();
		for (Users u : ls) {
			u.setMobile("00000000" + RandomUtil.getInt(10));
			usersService.save(u);
		}
		return true;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cleanAndGenerateUser")
	public AjaxResponse cleanAndGenerateUser() {
		//get all user
		List<Users> ls = usersService.getAll();
		for (Users users : ls) {
			usersService.delete(users.getUserId());
		}
		//generate users data
		insertUsers insertUsers = new insertUsers();
		List<Users> usersList = insertUsers.insertUsers();
		//default password = 909394
		usersService.saveAll(usersList);

		List<String> result = new ArrayList<>();
		String u;
		for (Users user : usersList) {
			u = user.getUsername() + "   [" + user.getPassword() + "]";
			result.add(u);
		}
		return AjaxResponse.success(result);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/updatePassword/")
	public AjaxResponse updatePassword(@RequestParam("password") String password) {
		//get all user
		log.info("password: {}", password);
		List<Users> ls = usersService.getAll();
		List<Boolean> result = new ArrayList<>();
		for (Users users : ls) {
			String encode = passwordEncoder.encode(password);
			users.setPassword(encode);
			usersService.save(users);
		}
		return AjaxResponse.success();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/validatePassword/{password}")
	public AjaxResponse validatePassword(@PathVariable String password) {
		log.info("password: {}", password);
		//get all user
		List<Users> ls = usersService.getAll();
		List<String> result = new ArrayList<>();
		for (Users users : ls) {
			boolean match = passwordEncoder.matches(password, users.getPassword());
			log.info("encode: {}", match);
			result.add(users.getUsername() + "[ " + match + " ]");
		}
		return AjaxResponse.success(result);
	}
}

