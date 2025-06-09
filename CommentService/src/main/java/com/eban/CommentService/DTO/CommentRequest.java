package com.eban.CommentService.DTO;

public class CommentRequest {
    private String feedId;
    private String parentCommentId;
    private String content;
    private String authorId;

    public CommentRequest() {
    }

    public CommentRequest(String feedId, String parentCommentId, String content) {
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public CommentRequest(String feedId, String parentCommentId, String content, String authorId) {
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.authorId = authorId;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
