package com.eban.AuthService.AuthConfig;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.annotation.Nullable;

public class MyGrpcAuthenticationReader implements GrpcAuthenticationReader {
    @Nullable
    @Override
    public Authentication readAuthentication(ServerCall<?, ?> call, Metadata headers) throws AuthenticationException {
        return null;
    }
}