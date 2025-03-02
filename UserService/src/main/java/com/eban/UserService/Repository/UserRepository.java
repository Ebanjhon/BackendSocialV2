package com.eban.UserService.Repository;

import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("""
                SELECT new com.eban.UserService.DTO.UserResponse(
                    u.userId, u.username, u.firstname, u.lastname,
                    u.email, u.avatar, u.active
                )
                FROM User u
                WHERE LOWER(u.username) = LOWER(:username)
            """)
    Optional<UserResponse> findUserByUsername(@Param("username") String username);
}
