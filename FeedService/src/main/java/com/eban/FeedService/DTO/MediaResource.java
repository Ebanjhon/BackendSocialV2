package com.eban.FeedService.DTO;

import java.util.List;

import com.eban.FeedService.Model.Feed;

public class MediaResource {
    private String feedId;
    private List<Media> resource;
    private Feed data;
    private UserInfo author;
    private ActionFeed action;

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

    public Feed getData() {
        return data;
    }

    public void setData(Feed data) {
        this.data = data;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public MediaResource(String feedId, List<Media> resource, Feed data) {
        this.feedId = feedId;
        this.resource = resource;
        this.data = data;
    }

    public MediaResource(String feedId, List<Media> resource, Feed data, UserInfo author) {
        this.feedId = feedId;
        this.resource = resource;
        this.data = data;
        this.author = author;
    }

    public MediaResource(String feedId, List<Media> resource, Feed data, UserInfo author, ActionFeed action) {
        this.feedId = feedId;
        this.resource = resource;
        this.data = data;
        this.author = author;
        this.action = action;
    }

    public ActionFeed getAction() {
        return action;
    }

    public void setAction(ActionFeed action) {
        this.action = action;
    }
}
