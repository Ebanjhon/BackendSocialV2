package com.eban.NotiService.Service.ServiceImpl;

import com.eban.NotiService.Model.Noti;
import com.eban.NotiService.Model.TypeNoti;
import com.eban.notification.grpc.NotificationServiceGrpc;
import com.eban.notification.grpc.SendNotificationRequest;
import com.eban.notification.grpc.SendNotificationResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Autowired
    private NotiServiceImpl notiService;

    @Override
    public void sendNotification(SendNotificationRequest request, StreamObserver<SendNotificationResponse> responseObserver) {
        System.out.println("TEST");
        System.out.println(request.getFeedId());
        try {
            // Chuyển enum từ gRPC → enum nội bộ
            TypeNoti type = TypeNoti.valueOf(request.getType().name());
            // Tạo Noti từ enum nội bộ và các dữ liệu khác
            Noti result = notiService.createNoti(
                    new Noti(type, request.getCreaterId(), request.getUserId(), request.getFeedId())
            );
            // Trả kết quả cho client
            SendNotificationResponse response = SendNotificationResponse.newBuilder()
                    .setSuccess(true)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            responseObserver.onError(new RuntimeException("Invalid notification type: " + request.getType()));
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
