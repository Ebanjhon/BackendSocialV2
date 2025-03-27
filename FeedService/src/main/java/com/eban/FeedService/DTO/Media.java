package com.eban.FeedService.DTO;

public class Media {
    private String mediaId;
    private String url;
    private String postId;
    private int width;
    private int height;
    private String mediaType;

    public Media() {
    }

    public Media(String mediaId, String url, String postId, int width, int height, String mediaType) {
        this.mediaId = mediaId;
        this.url = url;
        this.postId = postId;
        this.width = width;
        this.height = height;
        this.mediaType = mediaType;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
