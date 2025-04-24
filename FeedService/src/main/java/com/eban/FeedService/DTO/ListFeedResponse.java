package com.eban.FeedService.DTO;

import java.util.List;

import com.eban.FeedService.Model.Feed;

public class ListFeedResponse {
    private List<Media> resource;
    private Feed feedDetail;
    private String feedId;
    private UserInfo author;

    public ListFeedResponse() {
    }

    public ListFeedResponse(List<Media> resource, Feed feedDetail, String feedId, UserInfo author) {
        this.resource = resource;
        this.feedDetail = feedDetail;
        this.feedId = feedId;
        this.author = author;
    }

    public ListFeedResponse(List<Media> resource, Feed feedDetail, String feedId) {
        this.resource = resource;
        this.feedDetail = feedDetail;
        this.feedId = feedId;
    }

    public List<Media> getResource() {
        return resource;
    }

    public void setResource(List<Media> resource) {
        this.resource = resource;
    }

    public Feed getFeedDetail() {
        return feedDetail;
    }

    public void setFeedDetail(Feed feedDetail) {
        this.feedDetail = feedDetail;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }
}
