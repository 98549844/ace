package com.ace.restController;

import com.ace.exception.PasswordNotMatchException;
import com.ace.exception.UserNotFoundException;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Columns;
import com.ace.models.entity.Users;
import com.ace.service.DataBaseService;
import com.ace.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname: MariaDBController
 * @Date: 4/5/2021 10:39 下午
 * @Author: garlam
 * @Description:
 */


@RestController
@RequestMapping("/rest/mariadb")
//@Api(tags = "mariadb")
@Tag(name = "MariaDB")
public class MariaDBRestController {
    private final static Logger log = LogManager.getLogger(MariaDBRestController.class.getName());

    private final UsersService usersService;
    private final DataBaseService dataBaseService;

    @Autowired
    public MariaDBRestController(UsersService usersService, DataBaseService dataBaseService) {
        this.usersService = usersService;
        this.dataBaseService = dataBaseService;
    }



    @RequestMapping(value = "/tableList", method = RequestMethod.GET)
    public AjaxResponse getTableList() throws ClassNotFoundException {

        List<String> list = dataBaseService.getAllTableName();
        return AjaxResponse.success(list);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{table}")
    public AjaxResponse getColumnByTable(@PathVariable String table) throws ClassNotFoundException {

        List<Columns> list = dataBaseService.getColumnName(table);
        return AjaxResponse.success(list);
    }


    @RequestMapping(method = RequestMethod.GET)
    public AjaxResponse getConnection() {
        return AjaxResponse.success("getConnection success");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hibernate/getAll")
    public List<Users> getAllHibernate() {
        List<Users> ls = usersService.findAll();
        log.info("get records:" + ls.size());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mybatis/getById")
    public Users getAllByIdMybatis() throws UserNotFoundException, PasswordNotMatchException {
        Users u = new Users();
        u.setUserAccount("garlam".toLowerCase());
        Users ls = usersService.findByUserAccount(u);
        log.info(ls.getUsername());
        return ls;
    }

}

