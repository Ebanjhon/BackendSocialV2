package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.DTO.Media;
import com.eban.FeedService.DTO.MediaResource;
import com.example.grpc.MediaRequest;
import com.example.grpc.MediaResponse;
import com.example.grpc.MediaServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMediaResource {

    @GrpcClient("media-service")
    private MediaServiceGrpc.MediaServiceBlockingStub mediaServiceBlockingStub;

    // Đổi kiểu trả về thành List<MediaResource>
    public List<MediaResource> getMediaForFeeds(List<String> feedIds) {
        // Gửi request tới media-service
        MediaRequest request = MediaRequest.newBuilder()
                .addAllFeedIds(feedIds)
                .build();

        // Gọi gRPC để lấy danh sách media
        MediaResponse response = mediaServiceBlockingStub.getMediaByFeedIds(request);

        // Chuyển đổi từ gRPC `FeedMedia` thành `MediaResource`
        return response.getFeedMediaListList().stream()
                .map(feedMedia -> new MediaResource(
                        feedMedia.getFeedId(),
                        feedMedia.getResourceList().stream()
                                .map(media -> new Media(
                                        media.getMediaId(),
                                        media.getUrl(),
                                        media.getPostId(),
                                        media.getWidth(),
                                        media.getHeight(),
                                        media.getMediaType()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
