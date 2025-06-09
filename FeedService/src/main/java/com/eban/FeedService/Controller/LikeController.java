package com.eban.FeedService.Controller;

import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Model.Like;
import com.eban.FeedService.Service.ServiceImpl.FeedServiceImpl;
import com.eban.FeedService.Service.ServiceImpl.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;

    @Autowired
    private FeedServiceImpl feedService;

//    @PostMapping()
//    public ResponseEntity<Object> actionLikePost(@RequestBody String userId,@RequestBody String feedId){
//        try {
//            Feed feed = feedService.getFeedById(feedId);
//            Like like = new Like(userId, feed);
//            Like result = likeService.likeMedia(like);
//            return ResponseEntity.status(HttpStatus.CREATED).body(result);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Like không thành công!");
//        }
//    }

}