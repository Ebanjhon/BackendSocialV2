package com.eban.AuthService.DTO;

public class AuthRsp {
    private String username;
    private String role;

    public AuthRsp(String username, String role) {
        this.username = username;
        this.role = role;
    }

}
