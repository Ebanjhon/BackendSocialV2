package com.eban.UserService.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "\"chat\"")
public class Chat {
    @Id
    @Column(updatable = false, nullable = false)
    private String chatId = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "room_chat_id") // tên cột trong DB
    private RoomChat roomChat;

    @ManyToOne
    @JoinColumn(name = "user_id") // tên cột trong DB
    private User user;
}
