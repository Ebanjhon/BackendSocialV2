package com.eban.UserService.Repository;

import com.eban.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM \"user\" WHERE username = :username LIMIT 1", nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String userName);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
