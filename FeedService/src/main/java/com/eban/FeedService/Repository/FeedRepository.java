package com.eban.FeedService.Repository;

import com.eban.FeedService.Model.Feed;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    @Query("SELECT feed FROM Feed feed WHERE feed.feedId = :feedId")
    Feed findFeedById(@Param("feedId") String feedId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Feed f WHERE f.feedId = :feedId")
    void deleteFeedById(String feedId);

    Page<Feed> findByAuthorId(String authorId, Pageable pageable);

    @Query("SELECT f.feedId FROM Feed f ORDER BY f.createDay DESC")
    Page<String> findAllFeedIds(Pageable pageable);

    @Query("SELECT COUNT(f) FROM Feed f WHERE f.authorId = :authorId")
    long countFeedsByAuthorId(@Param("authorId") String authorId);

    @Query("SELECT f.feedId FROM Feed f WHERE f.authorId = :authorId ORDER BY f.createDay ASC")
    Page<String> findFeedIdsByAuthorId(@Param("authorId") String authorId, Pageable pageable);
}
