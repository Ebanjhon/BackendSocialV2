package com.eban.MediaService.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "media")
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "media_id", updatable = false, nullable = false)
    private String mediaId;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String authorID;

    @Column(nullable = false)
    private String postId;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private TypeMedia mediaType;

    public Media(String url, String postId, int width, int height, TypeMedia mediaType) {
        this.url = url;
        this.postId = postId;
        this.width = width;
        this.height = height;
        this.mediaType = mediaType;
    }

    public Media(String url, String authorID, String postId, int width, int height, TypeMedia mediaType) {
        this.url = url;
        this.authorID = authorID;
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

    public TypeMedia getMediaType() {
        return mediaType;
    }

    public void setMediaType(TypeMedia mediaType) {
        this.mediaType = mediaType;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
}
