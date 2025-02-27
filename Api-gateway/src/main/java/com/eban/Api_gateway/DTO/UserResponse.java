package com.eban.Api_gateway.DTO;

public class UserResponse {
    private String userId;
    private String username;
    private String email;
    private String role;

    // Constructor
    public UserResponse(String userId, String username, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
