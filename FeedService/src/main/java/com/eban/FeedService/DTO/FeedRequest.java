package com.eban.FeedService.DTO;

import lombok.Getter;
import lombok.Setter;

public class FeedRequest {
    private String authorId;
    private String content;

    public FeedRequest(String authorId, String content) {
        this.authorId = authorId;
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
