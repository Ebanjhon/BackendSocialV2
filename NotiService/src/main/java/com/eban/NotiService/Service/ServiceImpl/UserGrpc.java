package com.eban.NotiService.Service.ServiceImpl;

import com.eban.NotiService.DTO.User;
import com.eban.user.grpc.GetUserByIdRequest;
import com.eban.user.grpc.GetUserServiceGrpc;
import com.eban.user.grpc.UserInfoResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserGrpc {
    @GrpcClient("user-service")
    private GetUserServiceGrpc.GetUserServiceBlockingStub userStub;

    public User getUserById(String userId) {
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setUserId(userId)
                .build();

        try {
            UserInfoResponse response = userStub.getUserById(request);
            System.out.println(response.getUserId());
            return new User(
                    response.getUserId(),
                    response.getUsername(),
                    response.getFirstname(),
                    response.getLastname(),
                    response.getAvatar()
            );
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
