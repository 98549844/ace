package com.restController;

import com.models.common.AjaxResponse;
import com.models.entity.dao.Users;
import com.configDataGenerator.InsertUsers;
import com.service.RolesService;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: UserRestController
 * @Date: 5/7/2021 10:49 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/users")
@Api(tags = "users")
@EnableConfigurationProperties
public class UsersRestController {
    private static Logger log = LogManager.getLogger(UsersRestController.class.getName());


    private UsersService usersService;
    private RolesService rolesService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersRestController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public AjaxResponse getUsers() {
        List<Users> ls = usersService.getAll();
        return AjaxResponse.success(ls);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reinsertUser")
    public AjaxResponse reinsertUser() {
        //get all user
        List<Users> ls = usersService.getAll();
        usersService.deleteAll();

        //generate users data
        InsertUsers insertUsers = new InsertUsers();
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
    public AjaxResponse reEncodePassword(@RequestParam("password") String password) {
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

