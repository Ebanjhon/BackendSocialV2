package com.eban.UserService.Producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TokenProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendTokenForValidation(String token) {
        rabbitTemplate.convertAndSend("validate_token_queue", token);

        // Chờ nhận phản hồi từ `AuthService`
        Object response = rabbitTemplate.receiveAndConvert("validate_token_response_queue", 5000);
        return response != null ? response.toString() : "INVALID_TOKEN";
    }
}

