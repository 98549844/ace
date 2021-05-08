package com.controller;

import com.entity.dao.Test;
import com.mapper.TestMapper;
import com.service.TestService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sampleDataGenerator.DataGenerator.getTestEntity;

/**
 * @Classname: MariaDBController
 * @Date: 4/5/2021 10:39 下午
 * @Author: garlam
 * @Description:
 */


@RestController
@RequestMapping("/mariadb")
@Api(tags = "mariadb")
public class MariaDBRestController {
    private final static Logger log = LogManager.getLogger(MariaDBRestController.class.getName());


    private final TestService testService;
    private final TestMapper testMapper;

    @Autowired
    public MariaDBRestController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getConnection() {
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/hibernate/getAll")
    public List<Test> getAllHibernate() {
        List<Test> ls = testService.getAll();
        log.info("get records:" + ls.size());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hibernate/insert")
    public String inserthibernate() {
        List<Test> ls = getTestEntity();
        for (Test test : ls) {
            test.setId(null);
        }
        testService.saveAll(ls);
        return "Success insert: " + ls.size();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mybatis/getById")
    public Test getAByIdMybatis() {
        Test ls = testMapper.selectByPrimaryKey(10);
        log.info(ls.getUserName());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mybatis/insert")
    public String insertMybatis() {
        List<Test> ls = getTestEntity();
        for (Test test : ls) {
            test.setId(null);
            testMapper.insert(test);
        }
        return "Success insert: " + ls.size();
    }
}

