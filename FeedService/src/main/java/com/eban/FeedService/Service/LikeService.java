package com.eban.FeedService.Service;

import com.eban.FeedService.Model.Like;

public interface LikeService {
    void likeMedia(Like like);
    void dislike(String likeId);
}
