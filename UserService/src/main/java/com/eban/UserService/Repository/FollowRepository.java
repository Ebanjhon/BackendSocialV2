package com.eban.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eban.UserService.Model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {

    @Query(value = "SELECT * FROM \"follow\" WHERE user_id = :userId AND user_id_target = :userIdTarget", nativeQuery = true)
    Optional<Follow> findFollow(@Param("userId") String userId, @Param("userIdTarget") String userIdTarget);

    void deleteByUserIdAndUserIdTarget(String userId, String userIdTarget);
}
