package com.eban.NotiService.DTO;

import com.eban.NotiService.Model.TypeNoti;

public class NotiCreateRequest {
    private String userId, createrId;
    private TypeNoti type;

    public NotiCreateRequest() {}

    public NotiCreateRequest(String userId, String createrId, TypeNoti type) {
        this.userId = userId;
        this.createrId = createrId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public TypeNoti getType() {
        return type;
    }

    public void setType(TypeNoti type) {
        this.type = type;
    }
}
