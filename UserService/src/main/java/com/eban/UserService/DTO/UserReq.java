package com.eban.UserService.DTO;

import com.eban.UserService.Model.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserReq {
    private Gender gender;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String avatar;
    private String phone;
    private LocalDate birthDate;
    private boolean active = true;
    private LocalDateTime timeCreated;

    protected void onCreate() {
        this.timeCreated = LocalDateTime.now();
    }

    public UserReq() {}

    public UserReq(Gender gender, String username,String password, String firstname, String lastname, String email, String avatar, String phone, LocalDate birthDate, boolean active, LocalDateTime timeCreated) {
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
        this.birthDate = birthDate;
        this.active = active;
        this.timeCreated = timeCreated;
    }

    // getter and setter
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }
}
