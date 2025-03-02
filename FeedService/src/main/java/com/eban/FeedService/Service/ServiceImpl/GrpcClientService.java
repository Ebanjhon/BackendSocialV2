package com.eban.FeedService.Service.ServiceImpl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import hello.HelloServiceGrpc;
import hello.Hello;

@Service
public class GrpcClientService {
    private final HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub;

    public GrpcClientService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("user-service", 9090)
                .usePlaintext()
                .build();
        helloServiceStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public String sayHello(String name) {
        Hello.HelloRequest request = Hello.HelloRequest.newBuilder().setName(name).build();
        Hello.HelloResponse response = helloServiceStub.sayHello(request);
        return response.getMessage();
    }
}
