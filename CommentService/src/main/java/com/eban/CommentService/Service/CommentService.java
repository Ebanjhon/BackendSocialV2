package com.eban.CommentService.Service;

import java.util.List;

import com.eban.CommentService.DTO.CommentResponse;
import com.eban.CommentService.DTO.User;
import org.springframework.stereotype.Service;

import com.eban.CommentService.Model.Comment;

@Service
public interface CommentService {
    List<CommentResponse> getListComment(String feedId, int page, int size);

    List<CommentResponse> getListCommentChild(String commentParentId, int page, int size);

    Comment createComment(Comment comment);

    Boolean deleteCommentById(String commentId);

    Boolean DeleteCommentByFeedId(String feedId);

    void UpdateComment(Comment comment);

    User getUser(String userId);

    boolean isHasChildrenComment(String commentId);
}
