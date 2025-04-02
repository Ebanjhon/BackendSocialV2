package com.eban.AuthService.Service.ServiceImpl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import user.ActiveUserServiceGrpc;

@Service
public class ActiveUserService {
    private final ActiveUserServiceGrpc.ActiveUserServiceBlockingStub stub;

    public ActiveUserService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("user-service", 9092)
                .usePlaintext()
                .build();
        stub = ActiveUserServiceGrpc.newBlockingStub(channel);
    }

    public boolean activeAccountUser(String userId){
        user.ActiveUser.ActiveUserRequest request = user.ActiveUser.ActiveUserRequest.newBuilder()
                .setUserId(userId)
                .build();
        user.ActiveUser.ActiveUserResponse response = stub.activeUser(request);
        return response.getSuccess();
    }
}
