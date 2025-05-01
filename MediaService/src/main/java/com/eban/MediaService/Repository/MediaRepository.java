package com.eban.MediaService.Repository;

import com.eban.MediaService.model.Media;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, String> {
    List<Media> findByPostId(String postId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Media m WHERE m.postId = :postId")
    void deleteAllByPostId(String postId);
}
