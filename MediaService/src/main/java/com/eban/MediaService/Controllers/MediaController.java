package com.eban.MediaService.Controllers;

import com.eban.MediaService.DTO.MediaListResource;
import com.eban.MediaService.MinioService.MinioService;
import com.eban.MediaService.Service.MediaService;
import com.eban.MediaService.Service.ServiceImpl.ImageResizeService;
import com.eban.MediaService.Service.ServiceImpl.MediaServiceImpl;
import com.eban.MediaService.model.Media;
import com.eban.MediaService.model.TypeMedia;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private ImageResizeService imageResizeService;

    @Autowired
    private MediaServiceImpl mediaService;

    @Autowired
    private final MinioService minioService;

    public MediaController(MinioService minioService) {
        this.minioService = minioService;
    }

    private static final List<String> TYPES_IMGAE = Arrays.asList("image/png", "image/jpeg");
    private static final List<String> TYPES_VIDEO = Arrays.asList("video/mp4");

    @GetMapping
    public ResponseEntity<List<Media>> getlistMediaByFeedId(@RequestParam String feedId) {
        List<Media> listMedia = mediaService.getListByPostId(feedId);
        return ResponseEntity.status(HttpStatus.OK).body(listMedia);
    }

    @GetMapping("/resource")
    public ResponseEntity<Object> getListResourceMedia(){
        List<String> feedIds = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        try {
            List<MediaListResource> result = mediaService.getListResource(feedIds);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String postId)
            throws IOException {
        try {
            if (!TYPES_IMGAE.contains(file.getContentType())) {
                String fileUrl = uploadMediaImage(file, postId);
                return ResponseEntity.ok(fileUrl);
            } else if (!TYPES_VIDEO.contains(file.getContentType())) {
                String fileUrl = uploadMediaVideo(file, postId);
                return ResponseEntity.ok(fileUrl);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File không hợp lệ!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lưu không thành công!");
        }
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files,
            @RequestParam String postId) {
        List<String> fileUrls = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileUrl = null;
                if (TYPES_IMGAE.contains(file.getContentType())) {
                    fileUrl = uploadMediaImage(file, postId);
                } else if (TYPES_VIDEO.contains(file.getContentType())) {
                    fileUrl = uploadMediaVideo(file, postId);
                }
                if (fileUrl != null) {
                    fileUrls.add(fileUrl);
                }
            }
            if (fileUrls.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lưu file thất bại!");
            }
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lưu không thành công!");
        }
    }

    // get image size
    public int[] getImageDimensions(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            if (image != null) {
                return new int[] { image.getWidth(), image.getHeight() };
            } else {
                return new int[] { 100, 100 };
            }
        }
    }

    // upload images
    public String uploadMediaImage(MultipartFile file, String postId) throws IOException {
        int[] dimensions = getImageDimensions(file);
        try {
            if (dimensions[0] > 1000 || dimensions[1] > 1000) {
                MultipartFile fileResized = imageResizeService.resizeImage(file);
                dimensions = getImageDimensions(fileResized);
                String fileUrl = minioService.uploadFile(fileResized);
                Media media = new Media(fileUrl, postId, dimensions[0], dimensions[1], TypeMedia.IMAGE);
                mediaService.saveMedia(media);
                return fileUrl;
            } else {
                String fileUrl = minioService.uploadFile(file);
                Media media = new Media(fileUrl, postId, dimensions[0], dimensions[1], TypeMedia.IMAGE);
                mediaService.saveMedia(media);
                return fileUrl;
            }
        } catch (Exception e) {
            return null;
        }
    }

    // upload video
    public String uploadMediaVideo(MultipartFile file, String postId) {
        try {
            String fileUrl = minioService.uploadFile(file);
            Media media = new Media(fileUrl, postId, 0, 0, TypeMedia.IMAGE);
            mediaService.saveMedia(media);
            return fileUrl;
        } catch (Exception e) {
            return null;
        }
    }
}
