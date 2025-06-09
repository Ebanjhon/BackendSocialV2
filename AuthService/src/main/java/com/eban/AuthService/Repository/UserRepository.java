package com.eban.AuthService.Repository;

import com.eban.AuthService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM \"user\" WHERE username = :username LIMIT 1", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String userName);

    @Query(value = "SELECT * FROM \"user\" WHERE email = :email LIMIT 1", nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM \"user\" WHERE username = :input OR email = :input LIMIT 1", nativeQuery = true)
    Optional<User> findUserByUsernameOrEmail(@Param("input") String input);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
