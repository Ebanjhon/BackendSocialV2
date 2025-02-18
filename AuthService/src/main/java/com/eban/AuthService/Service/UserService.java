package com.eban.AuthService.Service;

import com.eban.AuthService.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> GetUserByUserName(String username);
    User saveUser(User user);
    Optional<User> authenticate(String username, String password);
    boolean isUserNameExist(String username);
    boolean isEmailExist(String email);
}
