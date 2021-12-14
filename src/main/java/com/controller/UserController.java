package com.controller;


import com.constant.Constant;
import com.constant.Css;
import com.controller.common.CommonController;
import com.models.entity.dao.Users;
import com.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.DateTimeUtil;
import util.NullUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace/users")
public class UserController extends CommonController {
    private static Logger log = LogManager.getLogger(UserController.class.getName());

    private UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        List<Users> userList = usersService.findAll();

        for (Users users : userList) {
            if (NullUtil.isNotNull(users.getBirthday())) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime birthDate = users.getBirthday();
                long age = DateTimeUtil.differenceYearsByLocalDateTime(birthDate, now);
                users.setAge(age);
            }
        }

        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        //submenu css control
        modelAndView.addObject(Css.open, Css.open);

        return modelAndView;
    }

//	@RequestMapping(value = "/{enable}.html", method = RequestMethod.POST)
//	public ModelAndView setEnable(@PathVariable String enable) {
//		log.info("enable {}", enable);
//		Map<String,String> map = new HashMap<>();
//		map.put("enable", enable);
//		ModelAndView modelAndView = page("ace/index.html");
//		return modelAndView;
//
//
//	}

    @RequestMapping(value = "/enable.html", method = RequestMethod.POST)
    public ModelAndView setEnable(@RequestParam(value = "userId") Long userId, @RequestParam(value = "enable") String enable) {
        log.info("userId {}", userId);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");

        Users user = usersService.findUsersById(userId);
        if (user.isEnabled()) {
            user.setEnabled(false);
            modelAndView.addObject("ajaxResult", "Disable");
        } else {
            user.setEnabled(true);
            modelAndView.addObject("ajaxResult", "Enable");
        }
        usersService.save(user);
        return modelAndView;
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {

        ModelAndView modelAndView = super.page("ace/modules/users/profile");
        return modelAndView;
    }

}
