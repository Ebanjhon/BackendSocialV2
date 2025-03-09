package com.eban.MediaService.Service.ServiceImpl;

import com.eban.MediaService.Repository.MediaRepository;
import com.eban.MediaService.Service.MediaService;
import com.eban.MediaService.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
