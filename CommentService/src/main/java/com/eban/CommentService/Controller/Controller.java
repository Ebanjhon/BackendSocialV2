package com.eban.CommentService.Controller;

import com.eban.CommentService.DTO.CommentRequest;
import com.eban.CommentService.Model.Comment;
import com.eban.CommentService.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class Controller {

    @Autowired
    private CommentService service;

    @GetMapping
    public ResponseEntity<Object> getListComment(@RequestParam String feedId) {
        try {
            List<Comment> data = service.getListCommentByFeedId(feedId);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail get list comment!" + e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentRequest data) {
        Comment comment = new Comment(data.getFeedId(), data.getParentCommentId(), data.getContent(), data.getUserId());
        try {
            service.createComment(comment);
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail Create Comment!" + e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestParam String commentId) {
        if(service.deleteCommentById(commentId))
            return ResponseEntity.status(HttpStatus.OK).body("Delete comment success!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to delete comment!");
    }
}
