package com.eban.UserService.Service.ServiceGRPC;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import user.ActiveUserServiceGrpc;

@GrpcService
public class ActiveUser extends ActiveUserServiceGrpc.ActiveUserServiceImplBase{
    @Override
    public void activeUser(user.ActiveUser.ActiveUserRequest request, StreamObserver<user.ActiveUser.ActiveUserResponse> responseObserver) {
        String userId = request.getUserId();
        boolean isActive = true;

        user.ActiveUser.ActiveUserResponse response = user.ActiveUser.ActiveUserResponse.newBuilder()
                .setSuccess(isActive)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
