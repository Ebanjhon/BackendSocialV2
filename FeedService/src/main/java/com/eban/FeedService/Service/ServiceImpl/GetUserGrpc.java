package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.DTO.UserInfo;
import com.eban.user.grpc.GetUserByIdRequest;
import com.eban.user.grpc.GetUserServiceGrpc;
import com.eban.user.grpc.UserInfoResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GetUserGrpc {
    @GrpcClient("user-service")
    private GetUserServiceGrpc.GetUserServiceBlockingStub userStub;

    public UserInfo getUserById(String userId) {
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setUserId(userId)
                .build();

        try {
            UserInfoResponse response = userStub.getUserById(request);

            return new UserInfo(
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
