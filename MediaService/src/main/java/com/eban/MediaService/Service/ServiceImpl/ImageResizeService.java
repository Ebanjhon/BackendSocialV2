package com.eban.MediaService.Service.ServiceImpl;

import com.eban.MediaService.model.MultipartFileCustom;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageResizeService {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    public MultipartFile resizeImage(MultipartFile imageFile) throws IOException {
        // Đọc dữ liệu ảnh từ MultipartFile
        InputStream inputStream = imageFile.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Resize ảnh về tối đa 1000x1000 (giữ tỷ lệ)
        Thumbnails.of(inputStream)
                .size(MAX_WIDTH, MAX_HEIGHT)
                .outputFormat("jpg")  // Đảm bảo output là JPG
                .toOutputStream(outputStream);

        return new MultipartFileCustom(
                outputStream.toByteArray(),            // Dữ liệu ảnh
                "resized-" + imageFile.getOriginalFilename(),  // Tên file mới
                "image/jpeg"                            // Kiểu MIME
        );
    }
}
