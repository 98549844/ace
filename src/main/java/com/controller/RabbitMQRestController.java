package com.controller;

import com.config.RabbitMQConfig;
import com.entity.dao.hibernate.TestEntity;
import com.entity.dao.hibernate.UserEntity;
import com.mq.rabbit.RabbitReceiver;
import com.mq.rabbit.RabbitSender;
import com.sampleDataGenerator.DataGenerator;
import com.service.TestService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private RabbitReceiver rabbitReceiver;
    @Autowired
    TestService testService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "direct/send")
    public void sendDirectQueue() {
        String ace = "[ ACE ]";

        for (int i = 0; i < 15; i++) {
            log.info(ace + "  " + "version::" + i);
            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, ace + "  " + "version::" + i);
        }
        int s =0;
        while (true){
            s++;
            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, ace + "  " + "version::" + s);
        }
    }


    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiverDirectQueue(String msg) {
        log.info("【receiverDirectQueue监听到消息】" + msg);
    }

}

