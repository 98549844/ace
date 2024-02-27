//package com.ace.restController;
//
//import com.ace.config.RabbitMQConfig;
//import com.ace.models.entity.Users;
//import com.ace.service.UsersService;
//import com.rabbitmq.client.Channel;
//import com.util.Console;
//import com.util.FastJson2Util;
//import com.util.ObjectUtil;
//import com.util.SleepUtil;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @Classname: RabbitRestController
// * @Date: 8/5/2021 12:10 上午
// * @Author: garlam
// * @Description:
// */
//
//
//@RestController
//@RequestMapping("/rest/rabbitmq")
//@Tag(name = "Rabbitmq")
//public class RabbitMQRestController {
//    private final static Logger log = LogManager.getLogger(RabbitMQRestController.class.getName());
//
//    private final AmqpTemplate amqpTemplate;
//    private final UsersService usersService;
//
//    @Autowired
//    public RabbitMQRestController(UsersService usersService, AmqpTemplate amqpTemplate) {
//        this.usersService = usersService;
//        this.amqpTemplate = amqpTemplate;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "direct/send/string")
//    public void sendDirectQueue() {
//        String ace = "[ ACE ]";
//        for (int i = 0; i < 10; i++) {
//            log.info(ace + "  " + "version::" + i);
//            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, ace + "  " + "version::" + i);
//            SleepUtil.sleep(1);
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "direct/send/user")
//    public void sendDirectUser() {
//        List<Users> users = usersService.findAll();
//        for (Users u : users) {
//            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE_USERS, u);
//            SleepUtil.sleep(1);
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "direct/send/message")
//    public void sendDirectMessage() {
//        List<Users> users = usersService.findAll();
//        for (Users u : users) {
//            //object转换成byte[]
//            Message msg = new Message(ObjectUtil.toByteArray(u));
//            this.amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGE, msg);
//            SleepUtil.sleep(1);
//        }
//    }
//
//    //发送者和接收者的queue name必须一致，不然不能接收
//    //queues是指要监听的队列的名字
//    @RabbitListener(queues = RabbitMQConfig.QUEUE)
//    public void receiverDirectQueue1(String msg) {
//        SleepUtil.sleep(3);
//        Console.println(RabbitMQConfig.QUEUE + "【监听到消息1】" + msg, Console.BOLD);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.QUEUE)
//    public void receiverDirectQueue2(String msg) {
//        SleepUtil.sleep(3);
//        Console.println(RabbitMQConfig.QUEUE + "【监听到消息2】" + msg, Console.BOLD);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_USERS)
//    public void receiverUsers(Users users) {
//        SleepUtil.sleep(3);
//        Console.println(RabbitMQConfig.QUEUE_USERS + "【监听到消息】" + users.getUsername(), Console.BOLD);
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGE)
//    public void receiverMessage(Channel channel, Message msg) {
//        try {
//            long msgTag = msg.getMessageProperties().getDeliveryTag();
//            Users users=  FastJson2Util.BytesArrayToObject(msg.getBody(),Users.class);
//            System.out.println(msgTag + ". " + RabbitMQConfig.QUEUE_MESSAGE + "【监听到消息】" + users.getUsername());
//            // 手动确认,只确认当条消息, 默认是自动确认
//            // channel.basicAck(msgTag, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
