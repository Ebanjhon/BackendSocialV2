package com.eban.UserService.Service.ServiceGRPC;

import org.springframework.beans.factory.annotation.Autowired;

import com.eban.UserService.Service.UserService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import user.ActiveUserServiceGrpc;

@GrpcService
public class ActiveUser extends ActiveUserServiceGrpc.ActiveUserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void activeUser(user.ActiveUser.ActiveUserRequest request,
            StreamObserver<user.ActiveUser.ActiveUserResponse> responseObserver) {
        String userId = request.getUserId();
        boolean result;
        try {
            userService.activeUserAccount(userId);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        user.ActiveUser.ActiveUserResponse response = user.ActiveUser.ActiveUserResponse.newBuilder()
                .setSuccess(result)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
