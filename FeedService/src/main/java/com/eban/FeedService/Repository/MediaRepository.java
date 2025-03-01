package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Media;
import com.eban.FeedService.Model.TypeMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    // Lấy danh sách Media theo feedId
    List<Media> findByFeedId(String feedId);

    // Lấy một Media theo mediaId
    Optional<Media> findByMediaId(String mediaId);

    // Kiểm tra tồn tại của Media theo mediaId
    boolean existsByMediaId(String mediaId);

    // Xóa Media theo mediaId
    void deleteByMediaId(String mediaId);

    // Lấy tất cả Media theo loại Media (typeMedia)
    List<Media> findByTypeMedia(TypeMedia typeMedia);

    // Lấy tất cả Media theo feedId và typeMedia
    List<Media> findByFeedIdAndTypeMedia(String feedId, TypeMedia typeMedia);

    // Truy vấn tùy chỉnh để lấy danh sách Media theo feedId và loại (sử dụng JPQL)
    @Query("SELECT m FROM Media m WHERE m.feedId = :feedId AND m.typeMedia = :typeMedia")
    List<Media> getMediaByFeedIdAndTypeMedia(String feedId, TypeMedia typeMedia);
}
