package com.eban.UserService.Model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

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
}
