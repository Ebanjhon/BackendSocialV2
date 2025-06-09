package com.eban.UserService.Service.ServiceGRPC;

import com.eban.notification.grpc.NotificationServiceGrpc;
import com.eban.notification.grpc.SendNotificationRequest;
import com.eban.notification.grpc.SendNotificationResponse;
import com.eban.notification.grpc.TypeNoti;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class NotiGrpc {
    @GrpcClient("noti-service")
    private NotificationServiceGrpc.NotificationServiceBlockingStub notificationStub;

    public boolean sendNotification(String userId, String createrId, TypeNoti type) {
        try {
            SendNotificationRequest request = SendNotificationRequest.newBuilder()
                    .setUserId(userId)
                    .setCreaterId(createrId)
                    .setType(type)
                    .build();

            SendNotificationResponse response = notificationStub.sendNotification(request);
            return response.getSuccess();
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
            return false;
        }
    }
}
