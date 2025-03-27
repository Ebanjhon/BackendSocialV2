package com.eban.MediaService.Service.ServiceImpl;

import com.example.grpc.MessageRequest;
import com.example.grpc.MessageResponse;
import com.example.grpc.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class DemoServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {
    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        String receivedMessage = request.getMessage();
        String responseText = "Hello: " + receivedMessage;

        MessageResponse response = MessageResponse.newBuilder()
                .setMessage(responseText)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
