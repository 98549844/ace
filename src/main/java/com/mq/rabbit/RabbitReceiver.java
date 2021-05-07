package com.mq.rabbit;

import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Classname: RabbitReceiver
 * @Date: 8/5/2021 12:05 上午
 * @Author: garlam
 * @Description:
 */

@Component
public class RabbitReceiver {
    private static Logger log = LogManager.getLogger(RabbitReceiver.class.getName());


    /**
     * 组合使用监听
     *
     * @param message
     * @param channel
     * @throws Exception
     * @RabbitListener @QueueBinding @Queue @Exchange
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue-1", durable = "true"), exchange = @Exchange(name = "exchange-1", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        //	1. 收到消息以后进行业务端消费处理
        System.err.println("-----------------------");
        System.err.println("消费消息:" + message.getPayload());

        //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
        //	spring.rabbitmq.listener.simple.acknowledge-mode=manual
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }


}

