package com.eban.AuthService.Service.ServiceImpl;

import com.eban.AuthService.Repository.UserRepository;
import com.eban.AuthService.Service.UserService;
import com.eban.AuthService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> GetUserByUserName(String username){
        return userRepo.findUserByUsername(username);
    }

    public User saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> authenticate(String userAccount, String password) {
        Optional<User> user = userRepo.findUserByUsernameOrEmail(userAccount);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public boolean isUserNameExist(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepo.existsByEmail(email);
    }
}
