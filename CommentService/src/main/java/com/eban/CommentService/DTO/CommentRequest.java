package com.eban.CommentService.DTO;

public class CommentRequest {
    private String feedId;
    private String parentCommentId;
    private String Content;

    public CommentRequest(String feedId, String parentCommentId, String content) {
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        Content = content;
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
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
