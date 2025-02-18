package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Model.User;
import com.eban.UserService.Repository.UserRepository;
import com.eban.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    public Optional<User> GetUserByUserName(String username){
        return userRepo.findUserByUsername(username);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
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
