package com.eban.FeedService.DTO;

public class FeedRequest {
    private String content;
    private String feedId;

    public FeedRequest() {
    }

    public FeedRequest(String content, String feedId) {
        this.content = content;
        this.feedId = feedId;
    }

    public FeedRequest(String content) {
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
