package com.xxx.xlt.config.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProducerConsumerConfig {
    @Bean
    public Queue myQueue() {
        Queue queue = new Queue("myqueue");
        return queue;
    }

}
