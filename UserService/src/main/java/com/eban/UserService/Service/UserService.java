package com.eban.UserService.Service;

import com.eban.UserService.DTO.SreachUser;
import com.eban.UserService.DTO.UserDetailResponse;
import com.eban.UserService.DTO.UserRequest;
import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<UserResponse> GetUserByUserName(String username);

    Optional<User> GetUserByUserId(String userId);

    Optional<UserDetailResponse> GetUserDetailByUserName(String username);

    User saveUser(UserRequest user);

    boolean isUserNameExist(String username);

    boolean isEmailExist(String email);

    void activeUserAccount(String userId);

    List<SreachUser> getListUserByUserName(String username, String userId);

}
