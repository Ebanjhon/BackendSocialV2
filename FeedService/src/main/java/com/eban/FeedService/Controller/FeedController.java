package com.eban.FeedService.Controller;

import com.eban.FeedService.DTO.FeedRequest;
import com.eban.FeedService.DTO.MediaResource;
import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Service.FeedService;

import java.util.List;
import java.util.Map;

import com.eban.FeedService.Service.ServiceImpl.DemoGrpc;
import com.eban.FeedService.Service.ServiceImpl.ListMediaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final DemoGrpc demoGrpc;
    private final ListMediaResource listMediaResource;

    public FeedController(DemoGrpc demoGrpc, ListMediaResource listMediaResource) {
        this.demoGrpc = demoGrpc;
        this.listMediaResource = listMediaResource;
    }

    @Autowired
    private FeedService feedService;

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
            @RequestParam String feedId) {
        try {
            Feed feed = feedService.getFeedById(feedId);
            if (feed.getAuthorId() == headers.get("x-user-id")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn không thể xóa bài viết người khác!");
            }
            feedService.deleteFeedById(feedId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi xóa bài viết!");
        }
        return ResponseEntity.noContent().build();
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
        List<MediaResource> result = listMediaResource.getMediaForFeeds(feedIds);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demoMessProto() {
        String result = demoGrpc.sendMessDemo("Jhon");
        return ResponseEntity.ok(result);
    }
}
