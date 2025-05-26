package com.eban.MediaService.Controllers;

import com.eban.MediaService.DTO.MediaListResource;
import com.eban.MediaService.MinioService.MinioService;
import com.eban.MediaService.Service.ServiceImpl.ImageResizeService;
import com.eban.MediaService.Service.ServiceImpl.MediaServiceImpl;
import com.eban.MediaService.model.Media;
import com.eban.MediaService.model.TypeMedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Object> getListResourceMedia() {
        List<String> feedIds = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        try {
            List<MediaListResource> result = mediaService.getListResource(feedIds);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getListMediaProfile(@RequestParam String authorId,
                                                      @RequestParam int page,
                                                      @RequestParam int size) {
      try {
          List<Media> result = mediaService.getListMediaProfile(authorId,page,size);
          return ResponseEntity.status(HttpStatus.OK).body(result);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
      }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestHeader Map<String, String> headers, @RequestParam("file") MultipartFile file, @RequestParam String postId)
            throws IOException {
        String authorId = headers.get("x-user-id");
        try {
            if (!TYPES_IMGAE.contains(file.getContentType())) {
                String fileUrl = uploadMediaImage(file, postId, authorId );
                return ResponseEntity.ok(fileUrl);
            } else if (!TYPES_VIDEO.contains(file.getContentType())) {
                String fileUrl = uploadMediaVideo(file, postId, authorId);
                return ResponseEntity.ok(fileUrl);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File không hợp lệ!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lưu không thành công!");
        }
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file)
            throws IOException {
        try {
            if (TYPES_IMGAE.contains(file.getContentType())) {
                String fileUrl = minioService.uploadFile(file);
                return ResponseEntity.ok(fileUrl);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File không hợp lệ!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lưu không thành công!");
        }
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestHeader Map<String, String> headers,@RequestParam("files") List<MultipartFile> files,
            @RequestParam String postId) {
        List<String> fileUrls = new ArrayList<>();
        String authorId = headers.get("x-user-id");
        try {
            for (MultipartFile file : files) {
                String fileUrl = null;
                if (TYPES_IMGAE.contains(file.getContentType())) {
                    fileUrl = uploadMediaImage(file, postId, authorId);
                } else if (TYPES_VIDEO.contains(file.getContentType())) {
                    fileUrl = uploadMediaVideo(file, postId, authorId);
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
    public String uploadMediaImage(MultipartFile file, String postId, String authorId) throws IOException {
        int[] dimensions = getImageDimensions(file);
        try {
            if (dimensions[0] > 1000 || dimensions[1] > 1000) {
                MultipartFile fileResized = imageResizeService.resizeImage(file);
                dimensions = getImageDimensions(fileResized);
                String fileUrl = minioService.uploadFile(fileResized);
                Media media = new Media(fileUrl, authorId, postId, dimensions[0], dimensions[1], TypeMedia.IMAGE);
                mediaService.saveMedia(media);
                return fileUrl;
            } else {
                String fileUrl = minioService.uploadFile(file);
                Media media = new Media(fileUrl, authorId, postId, dimensions[0], dimensions[1], TypeMedia.IMAGE);
                mediaService.saveMedia(media);
                return fileUrl;
            }
        } catch (Exception e) {
            return null;
        }
    }

    // upload video
    public String uploadMediaVideo(MultipartFile file, String postId, String AuthorId) {
        try {
            String fileUrl = minioService.uploadFile(file);
            Media media = new Media(fileUrl, AuthorId, postId, 0, 0, TypeMedia.IMAGE);
            mediaService.saveMedia(media);
            return fileUrl;
        } catch (Exception e) {
            return null;
        }
    }
}
