package com.eban.NotiService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"noti\"")
@NoArgsConstructor
@AllArgsConstructor
public class Noti {
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "notiId", updatable = false, nullable = false, unique = true)
    private String notiId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeNoti typeNotification;

    @Column(nullable = false)
    private String CreaterId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = true)
    private String feedId;

    @CreationTimestamp
    @Column(name = "created_day", nullable = false, updatable = false)
    private LocalDateTime createDay;

    public Noti(TypeNoti typeNotification, String createrId, String userId) {
        this.typeNotification = typeNotification;
        CreaterId = createrId;
        this.userId = userId;
    }

    public Noti(TypeNoti typeNotification, String createrId, String userId, String feedId) {
        this.typeNotification = typeNotification;
        CreaterId = createrId;
        this.userId = userId;
        this.feedId = feedId;
    }

    public String getNotiId() {
        return notiId;
    }

    public void setNotiId(String notiId) {
        this.notiId = notiId;
    }

    public TypeNoti getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNoti typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getCreaterId() {
        return CreaterId;
    }

    public void setCreaterId(String createrId) {
        CreaterId = createrId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateDay() {
        return createDay;
    }

    public void setCreateDay(LocalDateTime createDay) {
        this.createDay = createDay;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
