//package com.ace.restController;
//
//import com.util.SleepUtil;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//
///**
// * @Classname: KafkaRestController
// * @Date: 24/2/2024 10:21 pm
// * @Author: garlam
// * @Description: 参考资料 https://blog.csdn.net/cold___play/article/details/132398946
// */
//
//@RestController
//@RequestMapping("/rest/kafka")
//@Tag(name = "Kafka")
//public class KafkaRestController {
//    private static final Logger log = LogManager.getLogger(KafkaRestController.class.getName());
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    public KafkaRestController(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    //简单生产者
//    @GetMapping("/send/{message}")
//    public void sendMessage(@PathVariable("message") String message) {
//        for (int i = 0; i < 10; i++) {
//            log.info("Kafka send " + i);
//            kafkaTemplate.send("ace", message + " " + i);
//            SleepUtil.sleep(1);
//        }
//    }
//
//    /**
//     * 回调的第一种写法
//     *
//     * @param message
//     */
//    @GetMapping("/sendCallbackMessage/{message}")
//    public void sendCallbackMessage(@PathVariable("message") String message) {
//        //SpringBoot3的写法
//        CompletableFuture<SendResult<String, Object>> completableFuture = new CompletableFuture<>();
//        for (int i = 0; i < 10; i++) {
//            log.info("Kafka callback send " + i);
//            // kafkaTemplate.send("ace", message + " " + i);
//            completableFuture = kafkaTemplate.send("ace-callback", UUID.randomUUID().toString(), message + ": " + i);
//            SleepUtil.sleep(1);
//        }
//
//
//        //执行成功回调
//        completableFuture.thenAccept(result -> {
//            log.info("回调=> 发送成功");
//        });
//        //执行失败回调
//        completableFuture.exceptionally(e -> {
//            log.error("发送失败: {}", e.getMessage());
//            return null;
//        });
//        //springboot2 callback 写法
//        //https://blog.csdn.net/zwrlj527/article/details/129015111
//    }
//
//
//    //简单消费者
//    @KafkaListener(topics = {"ace"})
//    public void onMessage(ConsumerRecord<String, Object> record) {
//        String msg = "简单消费：" + record.topic() + "-" + record.partition() + "=" + record.value();
//        System.out.println(msg);
//    }
//
//    @KafkaListener(topics = {"ace-callback"})
//    public void onCallbackMessage(ConsumerRecord<String, Object> record) {
//        String msg = "简单回调消费：" + record.topic() + "-" + record.partition() + "=" + record.value();
//        System.out.println(msg);
//    }
//}
//
