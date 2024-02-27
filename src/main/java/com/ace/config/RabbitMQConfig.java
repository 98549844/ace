//package com.ace.config;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Classname: RabbitMQConfig
// * @Date: 8/5/2021 1:02 上午
// * @Author: garlam
// * @Description:
// */
//
//
//@Configuration
//public class RabbitMQConfig {
//    private static final Logger log = LogManager.getLogger(RabbitMQConfig.class.getName());
//
//    public static final String QUEUE = "direct_queue";
//    public static final String QUEUE_USERS = "queue_users";
//    public static final String QUEUE_MESSAGE = "queue_message";
//    public static final String TOPIC_QUEUE1 = "topic.queue1";
//    public static final String TOPIC_QUEUE2 = "topic.queue2";
//    public static final String TOPIC_EXCHANGE = "topic.exchange";
//
//    /**
//     * Direct模式
//     *
//     * @return
//     */
//
//    @Bean
//    public Queue directQueue() {
//        // 第一个参数是队列名字， 第二个参数是指是否持久化
//        return new Queue(QUEUE, true);
//    }
//
//    @Bean
//    public Queue QueueMessage() {
//        // 第一个参数是队列名字， 第二个参数是指是否持久化
//        return new Queue(QUEUE_MESSAGE, true);
//    }
//
//    @Bean
//    public Queue QueueUsers() {
//        // 第一个参数是队列名字， 第二个参数是指是否持久化
//        return new Queue(QUEUE_USERS, true);
//    }
//
//
//    /**
//     * Topic模式
//     *
//     * @return
//     */
//    @Bean
//    public Queue topicQueue1() {
//        return new Queue(TOPIC_QUEUE1);
//    }
//
//    @Bean
//    public Queue topicQueue2() {
//        return new Queue(TOPIC_QUEUE2);
//    }
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//
//    @Bean
//    public Binding topicBinding1() {
//        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("lzc.message");
//    }
//
//    @Bean
//    public Binding topicBinding2() {
//        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lzc.#");
//    }
//
//
//    //生產者对像转换设置
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        return rabbitTemplate;
//    }
//
//    //消费者对像转换设置
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        //设置手动ack模式 要和yml配置保持一致，不然会覆盖yml文件的配置
//       // factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }
//
//    // acknowledge-mode: none(无应答模式)
//    // 在这种模式下，不管消费者异常消费，
//    // 还是正常消费，MQ服务器中的队列都会自动删除已消费的消息
//
//    // acknowledge-mode: auto(自动应答模式)
//    // 当mq的应答模式配置为auto,或者没有进行配置时，
//    // 系统会默认为自动应答模式。在这种情况下，
//    // 只要我们的消费者，在消费消息的时候没有抛出异常，
//    // 那服务端MQ会认为，消息消费正常，删除队列中的消息；
//    // 如果消费过程中，抛出了异常，消息会进行自动补偿，重会队列头部，
//    // 再次被拉到消费者的缓冲区（prefetch count）,进行重复消费。
//    // 此时如果缓冲区的大小设置为1，那么整个队列就会被阻塞，
//    // unacked也会显示为1（单线程消费的情况下）。
//
//    // acknowledge-mode: manual（手动应答模式）
//    // 当设置为应手动应答时，我们需要在消费消息的时候手动告诉MQ我们消费的情况，
//    // 否者MQ会一直等待消费端的消息，如果一直没有应答，当消费数量达到缓冲区大小（prefetch count）后，队列会全部阻塞。
//
//
//}
//
