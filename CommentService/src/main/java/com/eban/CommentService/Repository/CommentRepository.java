package com.eban.CommentService.Repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT c FROM Comment c WHERE c.feedId = :feedId")
    Page<Comment> getCommentByFeedId(@Param("feedId") String feedId, Pageable pageable);

    @Query("SELECT COUNT(c) > 0 FROM Comment c WHERE c.parentCommentId = :parentId")
    boolean existsByParentCommentId(@Param("parentId") String parentId);

    @Query("SELECT c FROM Comment c WHERE c.parentCommentId = :parentId")
    Page<Comment> findCommentsByParentId(@Param("parentId") String parentId, Pageable pageable);
}
