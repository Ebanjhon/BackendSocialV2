package com.eban.FeedService.Model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"media\"")
public class Media {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "mediaId", updatable = false, nullable = false, unique = true)
    private String mediaId;

    @Column(name = "feedId", nullable = false)
    private String feedId;

    @Column(name = "media", nullable = false)
    private String media;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeMedia", nullable = false)
    private TypeMedia typeMedia;

    public Media() {
    }

    public Media(String feedId, TypeMedia typeMedia) {
        feedId = feedId;
        this.typeMedia = typeMedia;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        feedId = feedId;
    }

    public TypeMedia getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(TypeMedia typeMedia) {
        this.typeMedia = typeMedia;
    }

    
}
