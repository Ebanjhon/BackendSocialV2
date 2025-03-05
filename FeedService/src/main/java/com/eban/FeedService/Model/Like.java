package com.eban.FeedService.Model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"likes\"")
public class Like {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "likeId", updatable = false, nullable = false, unique = true)
    private String likeId;

    @ManyToOne
    @JoinColumn(name = "feedId", referencedColumnName = "feedId", nullable = false)
    private Feed feed;

    @Column(name = "userId", nullable = false)
    private String userId;

    public Like() {
    }

    public Like(String userId, Feed feed) {
        this.userId = userId;
        this.feed = feed;
    }
}
