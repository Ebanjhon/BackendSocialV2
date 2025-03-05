package com.eban.UserService.Repository;

import com.eban.UserService.DTO.UserDetailResponse;
import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // lấy thông tin user
    @Query("""
                SELECT new com.eban.UserService.DTO.UserResponse(
                    u.userId, u.username, u.firstname, u.lastname,
                    u.email, u.avatar, u.active
                )
                FROM User u
                WHERE LOWER(u.username) = LOWER(:username)
            """)
    Optional<UserResponse> findUserByUsername(@Param("username") String username);

    // lấy thông tin user chi tiết
    @Query("SELECT new com.eban.UserService.DTO.UserDetailResponse( " +
            "u.userId, u.username, u.firstname, u.lastname, u.email, u.avatar, u.active, u.dateJoid, " +
            "p.bio, p.gender, p.phoneNumber, p.birthDate) " +
            "FROM User u LEFT JOIN Profile p ON u.userId = p.userId " +
            "WHERE u.username = :username")
    Optional<UserDetailResponse> findUserDetailByUsername(@Param("username") String username);
}
