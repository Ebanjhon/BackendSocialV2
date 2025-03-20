package com.eban.MediaService.DTO;

import java.util.List;

import com.eban.MediaService.model.Media;

public class mediasResponse {
    private List<Media> mediaResoure;

    public mediasResponse(List<Media> mediaResoure) {
        this.mediaResoure = mediaResoure;
    }

    public List<Media> getMediaResoure() {
        return mediaResoure;
    }

    public void setMediaResoure(List<Media> mediaResoure) {
        this.mediaResoure = mediaResoure;
    }

}
