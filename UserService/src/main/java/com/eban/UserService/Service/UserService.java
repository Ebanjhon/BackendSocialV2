package com.eban.UserService.Service;

import com.eban.UserService.DTO.UserRequest;
import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<UserResponse> GetUserByUserName(String username);

    User saveUser(UserRequest user);

    boolean isUserNameExist(String username);

    boolean isEmailExist(String email);
}
