package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Repository.FeedRepository;
import com.eban.FeedService.Service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepo;

    public Feed saveFeed(Feed feed){
        return feedRepo.save(feed);
    }

    @Override
    public Feed getFeedById(String feedId) {
        return feedRepo.findFeedById(feedId);
    }

    @Override
    public void deleteFeedById(String feedId) {
        return;
    }
}
