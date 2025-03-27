package com.eban.FeedService.Service.ServiceImpl;

import com.example.grpc.MessageRequest;
import com.example.grpc.MessageResponse;
import com.example.grpc.MessageServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class DemoGrpc {
    @GrpcClient("media-service")
    private MessageServiceGrpc.MessageServiceBlockingStub messageServiceBlockingStub;

    public String sendMessDemo(String mess) {
        MessageRequest request = MessageRequest.newBuilder().setMessage(mess).build();
        MessageResponse response = messageServiceBlockingStub.sendMessage(request);
        return response.getMessage();
    }
}
