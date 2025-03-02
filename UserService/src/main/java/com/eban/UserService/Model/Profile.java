package com.eban.UserService.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "\"profile\"")
public class Profile {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = true)
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateJoid;

    @PrePersist
    protected void onCreate() {
        this.dateJoid = LocalDateTime.now();
    }

    public Profile() {
    }

    public Profile(String userId, String bio, Gender gender, String phoneNumber, LocalDate birthDate, LocalDateTime dateJoid) {
        this.userId = userId;
        this.bio = bio;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.dateJoid = dateJoid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDateTime getDateJoid() {
        return dateJoid;
    }

    public void setDateJoid(LocalDateTime dateJoid) {
        this.dateJoid = dateJoid;
    }
}
