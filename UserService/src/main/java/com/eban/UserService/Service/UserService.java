package com.eban.UserService.Service;

import com.eban.UserService.DTO.UserReq;
import com.eban.UserService.Model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> GetUserByUserName(String username);
    Optional<User> GetUserProfile(String token);
    User saveUser(UserReq user);
    boolean isUserNameExist(String username);
    boolean isEmailExist(String email);
}
