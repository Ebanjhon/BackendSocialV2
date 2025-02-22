//package com.eban.AuthService.Consumer;
//
//import com.eban.AuthService.AuthConfig.JwtTokenFilter;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TokenConsumer {
//    private final JwtTokenFilter jwtTokenFilter;
//    private final RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    public TokenConsumer( JwtTokenFilter jwtTokenFilter, RabbitTemplate rabbitTemplate) {
//        this.jwtTokenFilter = jwtTokenFilter;
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @RabbitListener(queues = "validate_token_queue")
//    public void receiveToken(String token) {
//        String username = jwtTokenFilter.validateToken(token);
//        String response = (username != null) ? username : "INVALID_TOKEN";
//        // Gửi kết quả xác thực ngược lại `UserService`
//        rabbitTemplate.convertAndSend("validate_token_response_queue", response);
//    }
//}
