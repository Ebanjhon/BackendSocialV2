package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Feed;
import com.eban.FeedService.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // Lấy tất cả các Like theo feedId
    List<Like> findByFeedId(Feed feed);

    // Lấy tất cả các Like của một userId cụ thể
    List<Like> findByUserId(String userId);

    // Lấy tất cả các Like cho một FeedId và UserId
    List<Like> findByFeedIdAndUserId(Feed feedId, String userId);

    // Lấy tất cả Like theo feedId (thêm câu lệnh @Query tùy chỉnh)
    @Query("SELECT l FROM Like l WHERE l.feedId.feedId = :feedId")
    List<Like> findLikesByFeedId(String feedId);

    // Xóa tất cả các Like theo userId
    void deleteByUserId(String userId);

    // Kiểm tra sự tồn tại của Like theo feedId và userId
    boolean existsByFeedIdAndUserId(Feed feedId, String userId);
}
