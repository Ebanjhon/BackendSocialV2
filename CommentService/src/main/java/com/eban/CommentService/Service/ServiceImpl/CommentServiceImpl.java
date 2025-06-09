package com.eban.CommentService.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eban.CommentService.DTO.CommentResponse;
import com.eban.CommentService.DTO.User;
import com.eban.notification.grpc.TypeNoti;
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

    @Autowired
    private NotiGrpc notiService;

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
    public Comment createComment(Comment comment, String authorId) {
        if(!comment.getUserId().equals(authorId)){
            notiService.sendNotification(authorId,comment.getUserId(), TypeNoti.COMMENT, comment.getFeedId());
        }
        return repository.save(comment);
    }

    @Override
    public Boolean deleteCommentById(String commentId) {
        Optional<Comment> cmt = repository.findById(commentId);
        if(cmt.isPresent()){
            repository.deleteById(commentId);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean DeleteCommentByFeedId(String feedId) {
        try {
            repository.deleteByFeedId(feedId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void UpdateComment(Comment comment) {
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
