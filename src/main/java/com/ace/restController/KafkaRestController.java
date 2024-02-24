package com.ace.restController;

import com.util.SleepUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * @Classname: KafkaRestController
 * @Date: 24/2/2024 10:21 pm
 * @Author: garlam
 * @Description:
 * 参考资料 https://blog.csdn.net/cold___play/article/details/132398946
 */

@RestController
@RequestMapping("/rest/kafka")
@Tag(name = "Kafka")
public class KafkaRestController {
    private static final Logger log = LogManager.getLogger(KafkaRestController.class.getName());
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaRestController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //简单生产者
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage(@PathVariable("message") String message) {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("ace", message + " " + i);
            SleepUtil.sleep(1);
        }
    }


    //简单消费者
    @KafkaListener(topics = {"ace"})
    public void onMessage(ConsumerRecord<String, Object> record) {
        String msg = "简单消费：" + record.topic() + "-" + record.partition() + "=" + record.value();
        System.out.println(msg);
    }
}

