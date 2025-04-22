package com.eban.CommentService.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eban.CommentService.Model.Comment;
import com.eban.CommentService.Repository.CommentRepository;
import com.eban.CommentService.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public List<Comment> getListCommentByFeedId(String feedId) {
        return repository.findByFeedId(feedId);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteCommentByFeedId'");
    }

    @Override
    public void UpdateComment(Comment comment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UpdateComment'");
    }

}
