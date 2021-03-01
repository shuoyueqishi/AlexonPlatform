package com.xxx.xlt.service.mq.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    private static Logger logger= LoggerFactory.getLogger(MqSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendDirect(Object msg) {
        logger.info("send direct Mq msg");
        rabbitTemplate.convertAndSend("directExchange","orange",msg);
        logger.info("send direct Mq msg successfully");
    }

    public void sendTopic(Object msg) {
        logger.info("send topic exchange Mq msg");
        rabbitTemplate.convertAndSend("myTopicExchange","*.orange.*",msg);
        logger.info("send topic exchange Mq msg successfully");
    }
}
