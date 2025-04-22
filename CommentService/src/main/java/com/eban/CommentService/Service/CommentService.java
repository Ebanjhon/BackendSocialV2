package com.eban.CommentService.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eban.CommentService.Model.Comment;

@Service
public interface CommentService {
    List<Comment> getListCommentByFeedId(String feedId);

    Comment createComment(Comment comment);

    Boolean deleteCommentById(String commentId);

    Boolean DeleteCommentByFeedId(String feedId);

    void UpdateComment(Comment comment);
}
