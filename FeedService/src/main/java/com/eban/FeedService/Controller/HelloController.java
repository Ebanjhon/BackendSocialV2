package com.eban.FeedService.Controller;

import com.eban.FeedService.Service.ServiceImpl.GrpcClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    private final GrpcClientService grpcClientService;
    public HelloController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        return grpcClientService.sayHello(name);
    }
}