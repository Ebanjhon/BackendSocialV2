package com.eban.UserService.DTO;

public class UserResponse {
    private String userId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String avatar;
    private Boolean isCurentUser;
    private Boolean isActive;

    public UserResponse() {
    }

    public UserResponse(String userId, String username, String firstname, String lastname, String email, String avatar,
            Boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.isActive = isActive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getCurentUser() {
        return isCurentUser;
    }

    public void setCurentUser(Boolean curentUser) {
        isCurentUser = curentUser;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
