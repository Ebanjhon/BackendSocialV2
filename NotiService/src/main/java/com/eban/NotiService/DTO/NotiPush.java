package com.eban.NotiService.DTO;

public class NotiPush {
    private String username, firstname, lastname, contentNoti;

    public NotiPush(String username, String firstname, String lastname, String contentNoti) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contentNoti = contentNoti;
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

    public String getContentNoti() {
        return contentNoti;
    }

    public void setContentNoti(String contentNoti) {
        this.contentNoti = contentNoti;
    }
}
