package com.eban.FeedService.DTO;

import java.util.List;

public class MediaResource {
    private String feedId;
    private List<Media> resource;

    public MediaResource() {
    }

    public MediaResource(String feedId, List<Media> resource) {
        this.feedId = feedId;
        this.resource = resource;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public List<Media> getResource() {
        return resource;
    }

    public void setResource(List<Media> resource) {
        this.resource = resource;
    }
}
