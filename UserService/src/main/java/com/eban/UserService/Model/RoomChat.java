package com.eban.UserService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "\"room\"")
public class RoomChat {
    @Id
    @Column(updatable = false, nullable = false)
    private String roomChatId = UUID.randomUUID().toString();

    @Column(nullable = true)
    private String nameRoom;

    @Column(nullable = false, updatable = true)
    private LocalDateTime chatLate;

    public RoomChat(String nameRoom, LocalDateTime chatLate) {
        this.nameRoom = nameRoom;
        this.chatLate = chatLate;
    }

    @OneToMany(mappedBy = "roomChat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();

    public String getRoomChatId() {
        return roomChatId;
    }

    public void setRoomChatId(String roomChatId) {
        this.roomChatId = roomChatId;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public LocalDateTime getChatLate() {
        return chatLate;
    }

    public void setChatLate(LocalDateTime chatLate) {
        this.chatLate = chatLate;
    }
}
