package com.eban.MediaService.Service;

import com.eban.MediaService.model.Media;

import java.util.List;

public interface MediaService {
    Media saveMedia(Media media);
    List<Media> getListByPostId(String postId);
}
