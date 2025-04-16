package com.eban.UserService.Model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"follow\"")
public class Follow {
    @Id
    @Column(updatable = false, nullable = false)
    private String followId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userIdTarget;

    public Follow() {
    }

    public Follow(String userId, String userIdTarget) {
        this.userId = userId;
        this.userIdTarget = userIdTarget;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdTarget() {
        return userIdTarget;
    }

    public void setUserIdTarget(String userIdTarget) {
        this.userIdTarget = userIdTarget;
    }
}
