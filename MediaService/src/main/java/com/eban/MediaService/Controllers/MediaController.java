package com.eban.MediaService.Controllers;

import com.eban.MediaService.MinioService.MinioService;
import com.eban.MediaService.Service.ServiceImpl.MediaServiceImpl;
import com.eban.MediaService.model.Media;
import com.eban.MediaService.model.TypeMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaServiceImpl mediaService;

    @Autowired
    private final MinioService minioService;

    public MediaController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = minioService.uploadFile(file);
        try {
            Media media = new Media(fileUrl, "123", 12, 34, TypeMedia.IMAGE);
            mediaService.saveMedia(media);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lưu không thành công!");
        }
    }
}
