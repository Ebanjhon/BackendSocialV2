package com.eban.CommentService.DTO;

public class CommentRequest {
    private String feedId;
    private String userId;
    private String parentCommentId;
    private String Content;

    public CommentRequest(String feedId, String userId, String parentCommentId, String content) {
        this.feedId = feedId;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        Content = content;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
