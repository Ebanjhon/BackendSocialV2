package com.eban.FeedService.Service;

import com.eban.FeedService.Model.Feed;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FeedService {
    Feed saveFeed(Feed feed);

    Feed getFeedById(String feedId);

    Boolean deleteFeedById(String feedId);

    Feed updateFeed(Feed feed, String content);

    Page<Feed> getListFeedByAuthor(String authorId, int page, int size);
}
