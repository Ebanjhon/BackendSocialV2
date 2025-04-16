package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Config.ApiEndpoints;
import com.eban.UserService.DTO.RegisterReq;
import com.eban.UserService.DTO.SreachUser;
import com.eban.UserService.DTO.UserDetailResponse;
import com.eban.UserService.DTO.UserRequest;
import com.eban.UserService.DTO.UserResponse;
import com.eban.UserService.Model.User;
import com.eban.UserService.Repository.UserRepository;
import com.eban.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<UserResponse> GetUserByUserName(String username) {
        return userRepo.findUserByUsername(username);
    }

    public User saveUser(UserRequest user) {
        RegisterReq authRequest = new RegisterReq(user.getUsername(), user.getPassword(), user.getEmail());
        ResponseEntity<String> response = restTemplate.postForEntity(ApiEndpoints.REGISTER_USER, authRequest,
                String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            User userCreate = new User(response.getBody(), user.getUsername(), user.getFirstname(), user.getLastname(),
                    user.getEmail());
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

    @Override
    public Optional<UserDetailResponse> GetUserDetailByUserName(String username) {
        return userRepo.findUserDetailByUsername(username);
    }

    @Override
    public void activeUserAccount(String userId) {
        User user = userRepo.findByUserId(userId);
        System.err.println("oke nhe");
        user.setActive(true);
        userRepo.save(user);
    }

    @Override
    public List<SreachUser> getListUserByUserName(String username, String userId) {
        return userRepo.searchUsersWithFollowStatus(username, userId);
    }
}
