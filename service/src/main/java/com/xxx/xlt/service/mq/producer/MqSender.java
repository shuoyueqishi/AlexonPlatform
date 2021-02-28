package com.xxx.xlt.service.mq.producer;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    void sendDirect(Object msg) {
        rabbitTemplate.convertAndSend("directExchange","orange",msg);
    }
}
