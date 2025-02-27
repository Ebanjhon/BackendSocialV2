package com.eban.FeedService.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"feed\"")
@Getter
@Setter
public class Feed {
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "feedId", updatable = false, nullable = false, unique = true)
    private String feedId;
    @Column(name = "authorId", nullable = false)
    private String authorId;
    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_day", nullable = false, updatable = false)
    private LocalDateTime createDay;

    public Feed(String authorId, String content) {
        this.authorId = authorId;
        this.content = content;
    }
}
