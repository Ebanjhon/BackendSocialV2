package com.eban.AuthService.DTO;

public class LoginRsp {
    private String username;
    private String pasSword;
    
    public LoginRsp(String username, String pasSword) {
        this.username = username;
        this.pasSword = pasSword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasSword() {
        return pasSword;
    }

    public void setPasSword(String pasSword) {
        this.pasSword = pasSword;
    }
}
