package com.eban.CommentService.Repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eban.CommentService.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByFeedId(String feedId);

    List<Comment> findByParentCommentId(String feedId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.feedId = ?1")
    void deleteByFeedId(String feedId);

}
