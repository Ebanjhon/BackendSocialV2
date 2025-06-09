package com.eban.CommentService.Controller;

import com.eban.CommentService.DTO.CommentRequest;
import com.eban.CommentService.DTO.CommentResponse;
import com.eban.CommentService.DTO.User;
import com.eban.CommentService.Model.Comment;
import com.eban.CommentService.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class Controller {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<Object> getListComment(@RequestParam String feedId,@RequestParam int page ) {
        try {
            List<CommentResponse> data = service.getListComment(feedId, page, 5);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail get list comment!" + e);
        }
    }

    @GetMapping("/child")
    public ResponseEntity<Object> getListCommentChild(@RequestParam String parentId,@RequestParam int page ) {
        try {
            List<CommentResponse> data = service.getListCommentChild(parentId, page, 5);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail get list comment!" + e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestHeader Map<String, String> headers,@RequestBody CommentRequest data) {
        String userId = headers.get("x-user-id");
        Comment comment = new Comment(data.getFeedId(), data.getParentCommentId(), data.getContent(), userId);
        try {
            service.createComment(comment, data.getAuthorId());
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail Create Comment!" + e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteComment(@RequestParam String commentId) {
        boolean result = service.deleteCommentById(commentId);
        if(result)
        {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam String userId) {
        User user = service.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("Failed to get user!");
        }
    }
}
