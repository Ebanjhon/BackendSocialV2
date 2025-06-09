package com.eban.AuthService.DTO;

public class LoginRequest {
    private String userInput;
    private String password;

    public LoginRequest(String userInput, String password) {
        this.userInput = userInput;
        this.password = password;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pasSword) {
        this.password = pasSword;
    }
}
