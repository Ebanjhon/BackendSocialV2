package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

    @Query("SELECT COUNT(l) FROM Like l WHERE l.feed.feedId = :feedId")
    int countByFeedId(String feedId);

    // Kiểm tra user đó đã like chưa
    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.feed.feedId = :feedId AND l.userId = :userId")
    boolean isUserLikeFeed(String feedId, String userId);

    @Query("SELECT l FROM Like l WHERE l.userId = :userId AND l.feed.feedId = :feedId")
    Optional<Like> findByUserIdAndFeedId(@Param("userId") String userId, @Param("feedId") String feedId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Like l WHERE l.feed.feedId = :feedId")
    void deleteAllByFeedId(@Param("feedId") String feedId);
}
