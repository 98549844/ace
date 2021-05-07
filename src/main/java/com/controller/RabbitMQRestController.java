package com.controller;

import com.entity.dao.hibernate.TestEntity;
import com.entity.dao.hibernate.UserEntity;
import com.mq.rabbit.RabbitSender;
import com.sampleDataGenerator.DataGenerator;
import com.service.TestService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname: RabbitRestController
 * @Date: 8/5/2021 12:10 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rabbitmq")
@Api(tags = "rabbitmq")
public class RabbitMQRestController {
    private static Logger log = LogManager.getLogger(RabbitMQRestController.class.getName());

    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    TestService testService;

    public String send() throws Exception {
        List<TestEntity> ls = DataGenerator.getTestEntity();
        testService.saveAll(ls);
        List<TestEntity> tlist = testService.getAll();
        log.info("FROM MARIA DB: {}", tlist.size());

        for (int i = 0; i < 6; i++) {
            rabbitSender.send(tlist, null);
        }


        return "send: " + tlist.size();
    }

}

