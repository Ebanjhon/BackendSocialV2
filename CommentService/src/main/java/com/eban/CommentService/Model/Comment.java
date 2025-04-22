package com.eban.CommentService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"comment\"")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id", nullable = false, updatable = false)
    private String commentId;

    @Column(name = "feed_id")
    private String feedId;

    @Column(name = "parent_comment_id")
    private String parentCommentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @PrePersist
    protected void onCreate() {
        this.dateCreate = LocalDateTime.now();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Comment() {
    }

    public Comment(String feedId, String parentCommentId, String content, String userId) {
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.userId = userId;
    }
}
