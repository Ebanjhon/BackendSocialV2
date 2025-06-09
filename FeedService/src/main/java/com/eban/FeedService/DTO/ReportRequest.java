package com.eban.FeedService.DTO;

public class ReportRequest {
    private String feedId, content;

    public ReportRequest(String feedId, String content) {
        this.feedId = feedId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
