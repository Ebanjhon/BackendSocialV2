package com.eban.NotiService.Configs;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyTextWebSocketHandler extends TextWebSocketHandler {

    // Lưu WebSocketSession theo userId
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            userSessions.put(userId, session);
            System.out.println("🔌 User " + userId + " connected with session: " + session.getId());
        } else {
            System.out.println("⚠️ User ID not found. Closing connection.");
            try {
                session.close(CloseStatus.BAD_DATA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            userSessions.remove(userId);
            System.out.println("❌ User " + userId + " disconnected.");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String userId = getUserIdFromSession(session);
        System.out.println("📩 Received from " + userId + ": " + message.getPayload());

        // Echo lại cho chính user đó
        if (session.isOpen()) {
            session.sendMessage(new TextMessage("Server received: " + message.getPayload()));
        }
    }

    public void sendMessageToUser(String userId, String message) {
        WebSocketSession session = userSessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                System.out.println("✅ Sent message to user " + userId);
            } catch (IOException e) {
                System.err.println("❌ Failed to send message to user " + userId);
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ No active session for user " + userId);
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        if (session.getUri() == null || session.getUri().getQuery() == null) return null;

        String[] queryParams = session.getUri().getQuery().split("&");
        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("userId")) {
                return keyValue[1];
            }
        }
        return null;
    }
}
