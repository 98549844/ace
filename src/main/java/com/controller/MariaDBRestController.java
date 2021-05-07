package com.controller;

import com.entity.dao.hibernate.TestEntity;
import com.service.TestService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import util.LogUtil;

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

    @Autowired
    public MariaDBRestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getConnection() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public List<TestEntity> getAll() {
        List<TestEntity> ls = testService.getAll();
        log.info("get records:" + ls.size());
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insert")
    public String insert() {
        List<TestEntity> ls = getTestEntity();
        for (TestEntity testEntity : ls) {
            testEntity.setId(null);
        }
        testService.saveAll(ls);
        return "Success insert: " + ls.size();
    }
}

