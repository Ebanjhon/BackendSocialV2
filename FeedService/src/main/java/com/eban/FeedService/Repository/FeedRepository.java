package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    @Query("SELECT feed FROM Feed feed WHERE feed.feedId = :feedId")
    Feed findFeedById(@Param("feedId") String feedId);
}
