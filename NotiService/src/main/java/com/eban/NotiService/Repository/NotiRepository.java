package com.eban.NotiService.Repository;

import com.eban.NotiService.Model.Noti;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotiRepository extends JpaRepository<Noti, String> {

    Page<Noti> findByUserId(String userId, Pageable pageable);

    @Query("SELECT n FROM Noti n WHERE n.notiId = :notiId")
    Optional<Noti> getNotiByNotiId(@Param("notiId") String notiId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Noti n WHERE n.notiId = :notiId")
    void deleteByNotiId(@Param("notiId") String notiId);
}
