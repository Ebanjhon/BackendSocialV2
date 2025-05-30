package com.eban.MediaService.Repository;

import com.eban.MediaService.model.Media;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {
    List<Media> findByPostId(String postId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Media m WHERE m.postId = :postId")
    void deleteAllByPostId(String postId);

    @Query("SELECT m FROM Media m WHERE m.authorID = :authorId")
    Page<Media> getListMediaByAuthorId(@Param("authorId") String authorId, Pageable pageable);
}
