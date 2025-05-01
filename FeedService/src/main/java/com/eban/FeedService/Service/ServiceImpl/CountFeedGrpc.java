package com.eban.FeedService.Service.ServiceImpl;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import userfeed.CountFeed;
import userfeed.CountFeedServiceGrpc;

@GrpcService
public class CountFeedGrpc extends CountFeedServiceGrpc.CountFeedServiceImplBase {
    @Autowired
    private FeedServiceImpl feedService;

    @Override
    public void countFeedsByUserId(CountFeed.CountFeedRequest request, StreamObserver<CountFeed.CountFeedResponse> responseObserver) {
        String userId = request.getUserId();

        long fakeCount = feedService.countFeedByUserId(userId);

        CountFeed.CountFeedResponse response = CountFeed.CountFeedResponse.newBuilder()
                .setCountFeed(fakeCount)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
