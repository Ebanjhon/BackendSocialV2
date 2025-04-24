package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

    @Query("SELECT COUNT(l) FROM Like l WHERE l.feed.feedId = :feedId")
    int countByFeedId(String feedId);

    // Kiểm tra user đó đã like chưa
    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.feed.feedId = :feedId AND l.userId = :userId")
    boolean isUserLikeFeed(String feedId, String userId);
}
