package com.restController;

import com.models.entity.dao.Users;
import com.sampleDataGenerator.DataGenerator;
import com.sampleDataGenerator.insertUsers;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
@Api(tags = "user")
@EnableConfigurationProperties
public class UsersRestController {
    private static Logger log = LogManager.getLogger(UsersRestController.class.getName());


    private UsersService usersService;

    @Autowired
    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public boolean getUsers() {
        List<Users> ls = usersService.getAll();
        for (Users u : ls) {
            u.setMobile("0000 0000"+ RandomUtil.getInt(10));
            usersService.save(u);
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save")
    public boolean save() {
        //generate users data
        List<Users> usersList = new ArrayList<>();
        insertUsers insertUsers = new insertUsers();
        usersList = insertUsers.insertUsers();

        usersService.deleteAll();

        usersService.saveAll(usersList);
        return true;
    }

}

