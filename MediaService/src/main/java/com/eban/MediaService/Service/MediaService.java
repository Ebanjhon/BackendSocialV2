package com.eban.MediaService.Service;

import com.eban.MediaService.DTO.MediaListResource;
import com.eban.MediaService.model.Media;

import java.util.List;

public interface MediaService {
    Media saveMedia(Media media);
    List<Media> getListByPostId(String postId);
    List<MediaListResource> getListResource(List<String> feedIds);
    void deleteAll(String feedId);
    List<Media> getListMediaProfile(String authorId, int page, int size);
}
