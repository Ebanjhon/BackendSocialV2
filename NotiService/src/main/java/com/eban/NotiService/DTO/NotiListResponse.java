package com.eban.NotiService.DTO;

import com.eban.NotiService.Model.Noti;

public class NotiListResponse {
    private Noti noti;
    private User user;

    public NotiListResponse(Noti noti, User user) {
        this.noti = noti;
        this.user = user;
    }

    public Noti getNoti() {
        return noti;
    }

    public void setNoti(Noti noti) {
        this.noti = noti;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
