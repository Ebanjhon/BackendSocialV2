package com.eban.MediaService.DTO;

import com.eban.MediaService.model.Media;

import java.util.List;

public class MediaListResource {
    private String feedId;
    private List<Media> Resource;

    public MediaListResource() {
    }

    public MediaListResource(String feedId, List<Media> resource) {
        this.feedId = feedId;
        Resource = resource;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public List<Media> getResource() {
        return Resource;
    }

    public void setResource(List<Media> resource) {
        Resource = resource;
    }
}
