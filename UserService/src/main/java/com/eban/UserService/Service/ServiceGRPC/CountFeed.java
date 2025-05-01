package com.eban.UserService.Service.ServiceGRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import userfeed.CountFeedServiceGrpc;

@Service
public class CountFeed {
    private final CountFeedServiceGrpc.CountFeedServiceBlockingStub stub;
    public CountFeed() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("feed-service", 9093)
                .usePlaintext()
                .build();

        stub = CountFeedServiceGrpc.newBlockingStub(channel);
    }

    public CountFeed(CountFeedServiceGrpc.CountFeedServiceBlockingStub stub) {
        this.stub = stub;
    }

    public long getCountFeedByUserId(String userId) {
        userfeed.CountFeed.CountFeedRequest request = userfeed.CountFeed.CountFeedRequest.newBuilder()
                .setUserId(userId)
                .build();

        userfeed.CountFeed.CountFeedResponse response = stub.countFeedsByUserId(request);
        return response.getCountFeed();
    }
}
