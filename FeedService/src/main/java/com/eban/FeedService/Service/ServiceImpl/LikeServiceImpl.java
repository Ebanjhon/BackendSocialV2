package com.eban.FeedService.Service.ServiceImpl;

import com.eban.FeedService.Model.Like;
import com.eban.FeedService.Repository.LikeRepository;
import com.eban.FeedService.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Like likeMedia(Like like) {
        return likeRepository.save(like);
    }

}
