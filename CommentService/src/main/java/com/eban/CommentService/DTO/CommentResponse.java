package com.eban.CommentService.DTO;

import com.eban.CommentService.Model.Comment;

public class CommentResponse {
    private Comment data;
    private User user;
    private boolean isHasChil;

    public CommentResponse() {
    }

    public CommentResponse(Comment data, User user) {
        this.data = data;
        this.user = user;
    }

    public CommentResponse(Comment data, User user, boolean isHasChil) {
        this.data = data;
        this.user = user;
        this.isHasChil = isHasChil;
    }

    public Comment getData() {
        return data;
    }

    public void setData(Comment data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isHasChil() {
        return isHasChil;
    }

    public void setHasChil(boolean hasChil) {
        isHasChil = hasChil;
    }
}
