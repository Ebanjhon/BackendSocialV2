package com.eban.MediaService.Service.ServiceImpl;

import com.eban.MediaService.DTO.MediaListResource;
import com.eban.MediaService.Repository.MediaRepository;
import com.eban.MediaService.Service.MediaService;
import com.eban.MediaService.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public List<Media> getListByPostId(String postId) {
        return mediaRepository.findByPostId(postId);
    }

    @Override
    public List<MediaListResource> getListResource(List<String> feedIds) {
        List<MediaListResource> resources = new ArrayList<>();
        for(String feedId: feedIds){
            List<Media> media = getListByPostId(feedId);
            MediaListResource resource = new MediaListResource(feedId,media);
            resources.add(resource);
        }
        return resources;
    }

    @Override
    public void deleteAll(String feedId) {
        mediaRepository.deleteAllByPostId(feedId);
    }

    @Override
    public List<Media> getListMediaProfile(String authorId, int page, int size) {
        return mediaRepository
                .getListMediaByAuthorId(authorId, PageRequest.of(page, size))
                .getContent();
    }
}
