package com.eban.UserService.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AUTH_QUEUE = "auth_queue";

    @Bean
    public Queue authQueue() {
        return new Queue(AUTH_QUEUE, false);
    }
}