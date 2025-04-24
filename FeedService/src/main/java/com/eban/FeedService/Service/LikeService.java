package com.eban.FeedService.Service;

import com.eban.FeedService.Model.Like;

public interface LikeService {
    Like likeMedia(Like like);

    Boolean isLikeFeed(String feedId, String userId);

    int totalLike(String feedId);
}
