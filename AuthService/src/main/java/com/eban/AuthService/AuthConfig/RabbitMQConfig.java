package com.eban.AuthService.AuthConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
        return args -> {
            try {
                rabbitTemplate.convertAndSend("test.exchange", "test.routingkey", "Test Message");
                System.out.println("✅ RabbitMQ connection successful!");
            } catch (Exception e) {
                System.err.println("❌ RabbitMQ connection failed: " + e.getMessage());
            }
        };
    }
}

