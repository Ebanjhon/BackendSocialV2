package com.eban.AuthService.AuthConfig;

import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GRPCConfig {
    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        return new MyGrpcAuthenticationReader();
    }
}

