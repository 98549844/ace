package com.restController;

import com.models.common.AjaxResponse;
import com.models.entity.dao.Users;
import com.generator.InsertUsers;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsersRestController {
    private static Logger log = LogManager.getLogger(UsersRestController.class.getName());

    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersRestController(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getUsers")
    public AjaxResponse getUsers() {
        List<Users> ls = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsersByMybatis")
    public AjaxResponse getUsersByMybatis() {
        List<Users> ls = usersService.findAllByMybatis();
        List<String> result = new ArrayList<>();
        for (Users user : ls) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllUser")
    public AjaxResponse deleteAllUser() {
        usersService.deleteAll();
        return AjaxResponse.success("All users deleted");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/insertUser")
    public AjaxResponse insertUser() {
        usersService.deleteAll();

        //generate users data
        InsertUsers insertUsers = new InsertUsers();
        List<Users> usersList = insertUsers.insertUsers();
        //default password = 909394
        usersService.saveAll(usersList);

        List<String> result = new ArrayList<>();
        for (Users user : usersList) {
            String u = user.getUsername() + "   [" + user.getPassword() + "]";
            result.add(u);
        }
        return AjaxResponse.success(result);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/reEncodePassword")
    public AjaxResponse reEncodePassword(@RequestParam("password") String password) {
        //get all user
        log.info("password: {}", password);
        List<Users> ls = usersService.findAll();

        List<Users> usersWithReEncodePassword = new ArrayList<>();
        for (Users users : ls) {
            String encode = passwordEncoder.encode(password);
            users.setPassword(encode);
            usersWithReEncodePassword.add(users);
        }
        usersService.saveAll(usersWithReEncodePassword);
        return AjaxResponse.success("All password re-encoded");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/validatePassword/{password}")
    public AjaxResponse validatePassword(@PathVariable String password) {
        log.info("password: {}", password);
        //get all user
        List<Users> ls = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users users : ls) {
            boolean match = passwordEncoder.matches(password, users.getPassword());
            log.info("encode: {}", match);
            result.add(users.getUsername() + "[ " + match + " ]");
        }
        return AjaxResponse.success(result);
    }


}

