package com.eban.FeedService.Controller;

import com.eban.FeedService.DTO.FeedRequest;
import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.status(HttpStatus.OK).body("ok nhe!");
    }

    @PostMapping
    public ResponseEntity<Object> createFeed(@RequestBody FeedRequest data){
        try {
            Feed feed = new Feed(data.getAuthorId(), data.getContent());
            feedService.saveFeed(feed);
            if(feed != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(feed);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi tạo bài viết!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: " + e.toString());
        }
    }
}
