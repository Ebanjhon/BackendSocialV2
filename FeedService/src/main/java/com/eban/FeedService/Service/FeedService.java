package com.eban.FeedService.Service;

import com.eban.FeedService.Model.Feed;

public interface FeedService {
    Feed saveFeed(Feed feed);
    Feed getFeedById(String feedId);
    void deleteFeedById(String feedId);
}
