package com.eban.MediaService.Service.ServiceImpl;

import com.example.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class getListResource extends MediaServiceGrpc.MediaServiceImplBase {

    @Autowired
    private MediaServiceImpl mediaService;

    @Override
    public void getMediaByFeedIds(MediaRequest request, StreamObserver<MediaResponse> responseObserver) {
        List<String> feedIds = request.getFeedIdsList();

        List<FeedMedia> feedMediaList = feedIds.stream()
                .map(feedId -> {
                    List<MediaResource> resources = mediaService.getListByPostId(feedId).stream()
                            .map(media -> MediaResource.newBuilder()
                                    .setMediaId(media.getMediaId())
                                    .setUrl(media.getUrl())
                                    .setPostId(media.getPostId())
                                    .setWidth(media.getWidth())
                                    .setHeight(media.getHeight())
                                    .setMediaType(media.getMediaType().name())
                                    .build())
                            .collect(Collectors.toList());

                    return FeedMedia.newBuilder()
                            .setFeedId(feedId)
                            .addAllResource(resources)
                            .build();
                })
                .collect(Collectors.toList());

        MediaResponse response = MediaResponse.newBuilder()
                .addAllFeedMediaList(feedMediaList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
