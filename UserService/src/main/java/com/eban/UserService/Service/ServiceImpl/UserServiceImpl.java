package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Config.ApiEndpoints;
import com.eban.UserService.DTO.AuthReq;
import com.eban.UserService.DTO.RegisterReq;
import com.eban.UserService.DTO.UserReq;
import com.eban.UserService.Model.User;
import com.eban.UserService.Repository.UserRepository;
import com.eban.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<AuthReq> AuthToken(String token) {
        try {
            String url = ApiEndpoints.AUTHOR_TOKEN + "?token=" + token;
            return restTemplate.getForEntity(url, AuthReq.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
       
    }

    public Optional<User> GetUserProfile(String token) {
        ResponseEntity<AuthReq> req = AuthToken(token);
        if (req != null) {
            return userRepo.findUserByUsername(req.getBody().getUsername());
        } else {
            return null;
        }
    }

    public Optional<User> GetUserByUserName(String username) {
        return userRepo.findUserByUsername(username);
    }

    public User saveUser(UserReq user) {
        RegisterReq authRequest = new RegisterReq(user.getUsername(), user.getPassword(), user.getEmail());
        ResponseEntity<String> response = restTemplate.postForEntity(ApiEndpoints.REGISTER_USER, authRequest, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            User userCreate = new User(user.getGender(), user.getUsername(), user.getFirstname(), user.getLastname(),
                    user.getEmail(), user.getAvatar(), user.getPhone(), user.getBirthDate());
            return userRepo.save(userCreate);
        } else {
            return null;
        }
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
