package com.eban.FeedService.Controller;

import com.eban.FeedService.DTO.FeedRequest;
import com.eban.FeedService.DTO.MediaResource;
import com.eban.FeedService.DTO.ReportRequest;
import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Model.Like;
import com.eban.FeedService.Model.Report;
import com.eban.FeedService.Service.FeedService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eban.FeedService.Service.ServiceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final ListMediaResource listMediaResource;

    public FeedController(ListMediaResource listMediaResource) {
        this.listMediaResource = listMediaResource;
    }

    @Autowired
    private GetUserGrpc userGrpc;

    @Autowired
    private FeedService feedService;

    @Autowired
    private LikeServiceImpl likeService;

    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping
    public ResponseEntity<Object> getFeedDetail(@RequestHeader Map<String, String> headers,
            @RequestParam String feedId) {
        try {
            Feed feed = feedService.getFeedById(feedId);
            return ResponseEntity.status(HttpStatus.OK).body(feed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: " +
                    e.toString());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createFeed(@RequestHeader Map<String, String> headers,
            @RequestBody FeedRequest data) {
        try {
            Feed feed = new Feed(headers.get("x-user-id"), data.getContent());
            feedService.saveFeed(feed);
            if (feed != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(feed);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi tạo bài viết!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi: " +
                    e.toString());
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFeed(
            @RequestHeader Map<String, String> headers,
            @RequestBody String feedId) {
        try {
            Feed feed = feedService.getFeedById(feedId);
            if(feed == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bài viết không tồn tại!");
            }
            if (!feed.getAuthorId().equals(headers.get("x-user-id"))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn không thể xóa bài viết người khác!");
            }
            boolean isDeleteLike = likeService.deleteByFeedId(feedId);
            if(!isDeleteLike){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi xóa thích bài viết!");
            }
            feedService.deleteFeedById(feedId);
            return ResponseEntity.status(HttpStatus.OK).body("Delete post success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi xóa bài viết!" + e);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateFeed(@RequestHeader Map<String, String> headers,
            @RequestBody FeedRequest data) {
        try {
            Feed feed = feedService.getFeedById(data.getFeedId());
            if (feed.getAuthorId() == headers.get("x-user-id")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Bạn không thể chỉnh sửa bài viết người khác!");
            }
            feed = feedService.updateFeed(feed, data.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(feed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi cập nhật bài viết!");
        }
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Page<Feed>> getFeedsByAuthor(
            @PathVariable String authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Feed> feeds = (Page<Feed>) feedService.getListFeedByAuthor(authorId, page, size);
        return ResponseEntity.ok(feeds);
    }

    @GetMapping("/resource")
    public ResponseEntity<Object> getMediaResources(@RequestBody List<String> feedIds) {
        List<MediaResource> result = listMediaResource.getMediaForFeeds(feedIds, "");
        return ResponseEntity.ok(result);
    }

    // get list for home
    @GetMapping("/search")
    public ResponseEntity<Object> getFeedList(
            @RequestHeader Map<String, String> headers,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        String userId = headers.get("x-user-id");
        List<String> feedIds = feedService.getListFeed(page, size).getContent();
        List<MediaResource> result = listMediaResource.getMediaForFeeds(feedIds, userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/action")
    public ResponseEntity<Object> actionLikePost(@RequestHeader Map<String, String> headers, @RequestBody String feedId){
        try {
            Feed feed = feedService.getFeedById(feedId);
            Like like = new Like(headers.get("x-user-id"), feed);
            Like result = likeService.likeMedia(like);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Like không thành công!");
        }
    }

    @DeleteMapping("/action")
    public ResponseEntity<String> disLike(@RequestHeader Map<String, String> headers, @RequestBody String feedId){
        try {
            if(likeService.unLike(feedId,headers.get("x-user-id")))
                return ResponseEntity.status(HttpStatus.OK).body("Success!");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail!");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getFeedListProfile(
            @RequestHeader Map<String, String> headers,
            @RequestParam String authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        String userId = headers.get("x-user-id");
        List<String> feedIds = feedService.getListFeedByUserId(authorId,page, size).getContent();
        List<MediaResource> result = listMediaResource.getMediaForFeeds(feedIds, userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/report")
    public ResponseEntity<Object> report(@RequestHeader Map<String, String> headers, @RequestBody ReportRequest data){
        String userId = headers.get("x-user-id");
        Feed feed = feedService.getFeedById(data.getFeedId());
        try {
            Report report = reportService.createReport(new Report(feed, userId, data.getContent()));
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail!");
        }
    }

    @GetMapping("/test-time")
    public ResponseEntity<?> testTime() {
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("new Date(): " + new Date());
        return ResponseEntity.ok("Check log");
    }

}
