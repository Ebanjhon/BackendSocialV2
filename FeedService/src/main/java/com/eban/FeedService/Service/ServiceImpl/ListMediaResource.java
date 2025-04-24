package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.DTO.ActionFeed;
import com.eban.FeedService.DTO.Media;
import com.eban.FeedService.DTO.MediaResource;
import com.eban.FeedService.DTO.UserInfo;
import com.eban.FeedService.Model.Feed;
import com.example.grpc.MediaRequest;
import com.example.grpc.MediaResponse;
import com.example.grpc.MediaServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMediaResource {

    @Autowired
    private GetUserGrpc userGrpc;

    @Autowired
    private FeedServiceImpl serviceImpl;

    @Autowired
    private LikeServiceImpl likeService;

    @GrpcClient("media-service")
    private MediaServiceGrpc.MediaServiceBlockingStub mediaServiceBlockingStub;

    public List<MediaResource> getMediaForFeeds(List<String> feedIds, String userIdCurent) {
        MediaRequest request = MediaRequest.newBuilder()
                .addAllFeedIds(feedIds)
                .build();

        MediaResponse response = mediaServiceBlockingStub.getMediaByFeedIds(request);
        List<MediaResource> mediaResources = new ArrayList<>();
        for (var feedMedia : response.getFeedMediaListList()) {
            List<Media> mediaList = new ArrayList<>();
            for (var media : feedMedia.getResourceList()) {
                Media m = new Media(
                        media.getMediaId(),
                        media.getUrl(),
                        media.getPostId(),
                        media.getWidth(),
                        media.getHeight(),
                        media.getMediaType()
                );
                mediaList.add(m);
            }
            Feed feed = serviceImpl.getFeedById(feedMedia.getFeedId());
            UserInfo user = userGrpc.getUserById(feed.getAuthorId());
            ActionFeed action = new ActionFeed(likeService.isLikeFeed(feedMedia.getFeedId(), userIdCurent),likeService.totalLike(feedMedia.getFeedId()));
            MediaResource mediaResource = new MediaResource(
                    feedMedia.getFeedId(),
                    mediaList,
                    feed,
                    user,
                    action
            );
            mediaResources.add(mediaResource);
        }
        return mediaResources;
    }
}
