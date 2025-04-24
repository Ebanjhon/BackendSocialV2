package com.eban.FeedService.DTO;

public class ActionFeed {
    private Boolean isLike;
    private int countLike;

    public ActionFeed(Boolean isLike, int countLike) {
        this.isLike = isLike;
        this.countLike = countLike;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean isLike) {
        this.isLike = isLike;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

}
