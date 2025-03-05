package com.eban.UserService.Repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eban.UserService.Model.Gender;
import com.eban.UserService.Model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Modifying
    @Query("""
            UPDATE Profile p
            SET p.bio = :bio,
                p.gender = :gender,
                p.phoneNumber = :phoneNumber,
                p.birthDate = :birthDate
            WHERE p.userId = :userId
            """)
    int updateProfileByUserId(@Param("userId") String userId,
            @Param("bio") String bio,
            @Param("gender") Gender gender,
            @Param("phoneNumber") String phoneNumber,
            @Param("birthDate") LocalDate birthDate);

}
