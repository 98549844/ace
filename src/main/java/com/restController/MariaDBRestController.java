package com.restController;

import com.models.entity.dao.Users;
import com.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.DataGenerator.DataGenerator.getTestEntity;

/**
 * @Classname: MariaDBController
 * @Date: 4/5/2021 10:39 下午
 * @Author: garlam
 * @Description:
 */


@RestController
@RequestMapping("/rest/mariadb")
@Api(tags = "mariadb")
public class MariaDBRestController {
    private final static Logger log = LogManager.getLogger(MariaDBRestController.class.getName());


    private UsersService usersService;


    @Autowired
    public MariaDBRestController(UsersService usersService) {
        this.usersService = usersService;
    }





    @RequestMapping(method = RequestMethod.GET)
    public String getConnection() {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/hibernate/getAll")
    public List<Users> getAllHibernate() {
        List<Users> ls = usersService.getAll();
        log.info("get records:" + ls.size());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hibernate/insert")
    public String inserthibernate() {
        List<Users> ls = getTestEntity();
        for (Users test : ls) {
            test.setUserId(null);
        }
        usersService.saveAll(ls);
        return "Success insert: " + ls.size();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mybatis/getById")
    public Users getAByIdMybatis() {
        Users u = new Users();
        u.setUserAccount("garlam".toLowerCase());
        Users ls = usersService.findByUserAccount(u);
        log.info(ls.getUsername());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mybatis/insert")
    public String insertMybatis() {
        List<Users> ls = getTestEntity();
        for (Users test : ls) {
            test.setUserId(null);
        }
        usersService.saveAll(ls);
        return "Success insert: " + ls.size();
    }
}

