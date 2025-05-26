package com.eban.UserService.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.eban.UserService.Model.Gender;

public class UserDetailResponse{
    private String userId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String avatar;
    private String cover;
    private boolean active;
    private LocalDateTime dateJoid;
    private String bio;
    private Gender gender;
    private String phoneNumber;
    private LocalDate birthDate;
    private Boolean isCurentUser;
    private Long countFeed;
    private Long countFollow;
    private Long countFollowing;

    public UserDetailResponse() {
    }

    public UserDetailResponse(String userId, String username, String firstname, String lastname, String email, String avatar, boolean active, LocalDateTime dateJoid, String bio, Gender gender, String phoneNumber, LocalDate birthDate) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.active = active;
        this.dateJoid = dateJoid;
        this.bio = bio;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public UserDetailResponse(String userId, String username, String firstname, String lastname, String email, String avatar, String cover, boolean active, LocalDateTime dateJoid, String bio, Gender gender, String phoneNumber, LocalDate birthDate) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.cover = cover;
        this.active = active;
        this.dateJoid = dateJoid;
        this.bio = bio;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getDateJoid() {
        return dateJoid;
    }

    public void setDateJoid(LocalDateTime dateJoid) {
        this.dateJoid = dateJoid;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getCurentUser() {
        return isCurentUser;
    }

    public void setCurentUser(Boolean curentUser) {
        isCurentUser = curentUser;
    }

    public Long getCountFeed() {
        return countFeed;
    }

    public void setCountFeed(Long countFeed) {
        this.countFeed = countFeed;
    }

    public Long getCountFollow() {
        return countFollow;
    }

    public void setCountFollow(Long countFollow) {
        this.countFollow = countFollow;
    }

    public Long getCountFollowing() {
        return countFollowing;
    }

    public void setCountFollowing(Long countFollowing) {
        this.countFollowing = countFollowing;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
