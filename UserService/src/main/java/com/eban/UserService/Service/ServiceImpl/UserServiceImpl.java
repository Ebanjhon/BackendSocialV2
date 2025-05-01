package com.eban.UserService.Service.ServiceImpl;

import com.eban.UserService.Config.ApiEndpoints;
import com.eban.UserService.DTO.*;
import com.eban.UserService.Model.User;
import com.eban.UserService.Repository.UserRepository;
import com.eban.UserService.Service.ServiceGRPC.CountFeed;
import com.eban.UserService.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Repository
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountFeed countFeed;

    @Autowired
    private FollowServiceImpl followService;

    @Override
    public Optional<UserResponse> GetUserByUserName(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    public Optional<User> GetUserByUserId(String userId) {
        return userRepo.findByUserId(userId);
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
        Optional<UserDetailResponse> user = userRepo.findUserDetailByUsername(username);
        if(user.isPresent()){
            user.get().setCountFeed(countFeed.getCountFeedByUserId(user.get().getUserId()));
            user.get().setCountFollow(followService.countFollower(user.get().getUserId()));
            user.get().setCountFollowing(followService.countFollowing(user.get().getUserId()));
            return user;
        }
        return null;
    }

    @Override
    public void activeUserAccount(String userId) {
        Optional<User> user = userRepo.findByUserId(userId);
        user.get().setActive(true);
        userRepo.save(user.get());
    }

    @Override
    public List<SreachUser> getListUserByUserName(String username, String userId) {
        return userRepo.searchUsersWithFollowStatus(username, userId);
    }

    @Override
    public User updateUser(UpdateUser user) {
        Optional<User> u = userRepo.findByUserId(user.getUserId());

        if (u.isPresent()) {
            User existingUser = u.get();
            existingUser.setAvatar(user.getAvatar());
            existingUser.setCover(user.getCover());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            return userRepo.save(existingUser);
        }
        throw new RuntimeException("User not found with id: " + user.getUserId());
    }

}
