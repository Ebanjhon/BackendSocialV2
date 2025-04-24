package com.eban.UserService.Service.ServiceGRPC;

import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import com.eban.UserService.Service.ServiceImpl.UserServiceImpl;
import com.eban.user.grpc.GetUserByIdRequest;
import com.eban.user.grpc.GetUserServiceGrpc;
import com.eban.user.grpc.UserInfoResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
public class GetUserGRPC extends GetUserServiceGrpc.GetUserServiceImplBase {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void getUserById(GetUserByIdRequest request, StreamObserver<UserInfoResponse> responseObserver) {
        Optional<User> user = userService.GetUserByUserId(request.getUserId());

        if (user.isEmpty()) {
            // Trường hợp không tìm thấy user, trả về phản hồi trống
            UserInfoResponse response = UserInfoResponse.newBuilder()
                    .setUserId("")  // Không dùng null, thay bằng chuỗi rỗng
                    .setUsername("")
                    .setFirstname("")
                    .setLastname("")
                    .setAvatar("")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;  // Kết thúc phương thức khi không tìm thấy user
        }

        // Trường hợp tìm thấy user
        User foundUser = user.get();
        UserInfoResponse response = UserInfoResponse.newBuilder()
                .setUserId(foundUser.getUserId())
                .setUsername(foundUser.getUsername())
                .setFirstname(foundUser.getFirstname())
                .setLastname(foundUser.getLastname() != null ? foundUser.getLastname() : "")
                .setAvatar(foundUser.getAvatar() != null ? foundUser.getAvatar() : "")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
