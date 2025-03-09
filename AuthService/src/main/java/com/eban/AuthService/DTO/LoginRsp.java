package com.eban.AuthService.DTO;

public class LoginRsp {
    private String username;
    private String password;

    public LoginRsp(String username, String pasSword) {
        this.username = username;
        this.password = pasSword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pasSword) {
        this.password = pasSword;
    }
}
