package com.xxx.xlt.service.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {
    private static Logger logger = LoggerFactory.getLogger(MqConsumer.class);

    @RabbitListener(queues = "topicQueue1")
    @RabbitHandler
    void topicHandler1(Object msg){
        logger.info("receive message is {}",msg.toString());
    }
    @RabbitListener(queues = "topicQueue2")
    @RabbitHandler
    void topicHandler2(Object msg){
        logger.info("receive message is {}",msg.toString());
    }
    @RabbitListener(queues = "directQueue1")
    @RabbitHandler
    void directHandler1(Object msg){
        logger.info("receive message is {}",msg.toString());
    }
    @RabbitListener(queues = "directQueue2")
    @RabbitHandler
    void directHandler2(Object msg){
        logger.info("receive message is {}",msg.toString());
    }
}
