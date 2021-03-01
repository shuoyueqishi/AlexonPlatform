package com.xxx.xlt.service.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topicQueue1")
public class TopicMqConsumer {
    private static Logger logger = LoggerFactory.getLogger(TopicMqConsumer.class);

    @RabbitHandler
    void processMsq(Object msg){
        logger.info("receive message is {}",msg.toString());
    }
}
