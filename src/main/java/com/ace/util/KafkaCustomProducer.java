package com.ace.util;

import com.ace.controller.common.CommonController;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


/**
 * @Classname: KafkaCustomProducer
 * @Date: 23/2/2024 11:20 pm
 * @Author: garlam
 * @Description:
 */


public class KafkaCustomProducer extends CommonController {
    private static final Logger log = LogManager.getLogger(KafkaCustomProducer.class.getName());

    //docker exec -it kafka1 /bin/bash
    //kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ace

    public static void main(String[] args) {
        //1、创建kafka的生产者配置对象
        Properties properties = new Properties();

        //2、添加对象配置参数：bootstrap.servers, key与value序列化器
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.100:9094");
        //key,value序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //3、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //4、send方法进行发送
        for (int i = 0; i < 10; i++) {
            kafkaProducer.send(new ProducerRecord<>("ace", "ace application version: 3.2.2." + i));
        }

        //5、关闭资源
        kafkaProducer.close();
    }

}

