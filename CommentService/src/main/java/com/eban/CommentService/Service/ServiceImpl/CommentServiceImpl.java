package com.eban.CommentService.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.eban.CommentService.DTO.CommentResponse;
import com.eban.CommentService.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eban.CommentService.Model.Comment;
import com.eban.CommentService.Repository.CommentRepository;
import com.eban.CommentService.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private GetUserGrpc getUserGrpc;

    @Override
    public List<CommentResponse> getListComment(String feedId, int page, int size) {
        List<Comment> comments = repository
                .getCommentByFeedId(feedId, PageRequest.of(page, size))
                .getContent();
        List<CommentResponse> data = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse cmt = new CommentResponse();
            User user = getUserGrpc.getUserById(comment.getUserId());
            cmt.setData(comment);
            cmt.setUser(user);
            cmt.setHasChil(isHasChildrenComment(comment.getCommentId()));
            data.add(cmt);
        }
        return data;
    }

    @Override
    public List<CommentResponse> getListCommentChild(String commentParentId, int page, int size) {
        List<Comment> comments = repository
                .findCommentsByParentId(commentParentId, PageRequest.of(page, size))
                .getContent();
        List<CommentResponse> data = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse cmt = new CommentResponse();
            User user = getUserGrpc.getUserById(comment.getUserId());
            cmt.setData(comment);
            cmt.setUser(user);
            data.add(cmt);
        }
        return data;
    }

    @Override
    public Comment createComment(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Boolean deleteCommentById(String commentId) {
        try {
            repository.deleteById(commentId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean DeleteCommentByFeedId(String feedId) {
        throw new UnsupportedOperationException("Unimplemented method 'DeleteCommentByFeedId'");
    }

    @Override
    public void UpdateComment(Comment comment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateComment'");
    }

    @Override
    public User getUser(String userId) {
        return getUserGrpc.getUserById(userId);
    }

    @Override
    public boolean isHasChildrenComment(String commentId) {
        return repository.existsByParentCommentId(commentId);
    }

}
