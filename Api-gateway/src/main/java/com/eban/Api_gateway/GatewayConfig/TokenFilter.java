package com.eban.Api_gateway.GatewayConfig;

import com.eban.Api_gateway.DTO.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TokenFilter implements WebFilter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    private final WebClient webClient;

    public TokenFilter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://auth-service:8080").build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/api/auth/login")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return webClient.post()
                    .uri("/api/auth/validate-token")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .flatMap(userResponse -> {
                        UserDetails userDetails = User.withUsername(userResponse.getUsername())
                                .password("")
                                .authorities(userResponse.getRole())
                                .build();

                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        logger.info("✅ User xác thực: {}, Role: {}", userResponse.getUsername(),
                                userResponse.getRole());

                        System.out.println(userResponse);

                        ServerWebExchange modifiedExchange = exchange.mutate()
                                .request(builder -> builder
                                        .header("X-User-Id", userResponse.getUserId())
                                        .header("X-Username", userResponse.getUsername())
                                        .header("X-Email", userResponse.getEmail())
                                        .header("X-Role", userResponse.getRole()))
                                .build();

                        return chain.filter(modifiedExchange)
                                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                    })
                    .doOnError(error -> logger.error("❌ Lỗi khi xác thực token: {}", error.getMessage()))
                    .onErrorResume(error -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        }
        logger.warn("⚠️ Không có token trong request!");
        return chain.filter(exchange);
    }
}
