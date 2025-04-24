package com.eban.FeedService.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"feed\"")
public class Feed {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "feedId", updatable = false, nullable = false, unique = true)
    private String feedId;

    @Column(name = "authorId", nullable = false)
    private String authorId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_day", nullable = false, updatable = false)
    private LocalDateTime createDay;

    public Feed() {

    }

    public Feed(String authorId, String content) {
        this.authorId = authorId;
        this.content = content;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDay() {
        return createDay;
    }

    public void setCreateDay(LocalDateTime createDay) {
        this.createDay = createDay;
    }
}
