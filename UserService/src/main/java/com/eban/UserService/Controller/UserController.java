package com.eban.UserService.Controller;

import com.eban.UserService.DTO.*;
import com.eban.UserService.Model.Follow;
import com.eban.UserService.Model.Profile;
import com.eban.UserService.Model.User;
import com.eban.UserService.Service.ServiceImpl.FollowServiceImpl;
import com.eban.UserService.Service.ServiceImpl.ProfileServiceImpl;
import com.eban.UserService.Service.ServiceImpl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private FollowServiceImpl followService;

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserRequest user) {
        if (userService.isUserNameExist(user.getUsername()) || userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tên người dùng và email đã có sẳn!");
        }
        User userCreated = userService.saveUser(user);
        if (userCreated != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hiện tại không thể tạo tài khoản người dùng!");
    }

    @GetMapping
    public ResponseEntity<Object> getUserProfile(@RequestParam String username,
            @RequestHeader Map<String, String> headers) {
        Optional<UserResponse> user = userService.GetUserByUserName(username);
        if (user.isPresent()) {
            user.get().setCurentUser(username == headers.get("x-username"));
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy user!");
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<Object> saveUserProfile(@RequestHeader Map<String, String> headers,
            @RequestBody Profile profile) {
        try {
            Profile p = profileService.saveProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin không hợp lệ!");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfileDetial(@RequestHeader Map<String, String> headers,
            @RequestParam String username) {
        try {
            Optional<UserDetailResponse> result = userService.GetUserDetailByUserName(username);
            result.get().setCurentUser(headers.get("x-username") == username);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy user!");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchAccount(@RequestHeader Map<String, String> headers,
            @RequestParam String username) {
        try {
            String userId = headers.get("x-user-id");
            List<SreachUser> results = userService.getListUserByUserName(username, userId);
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy users!");
        }
    }

    @PostMapping("/follow")
    public ResponseEntity<Object> follow(@RequestHeader Map<String, String> headers,
            @RequestBody FollowRequest userTargetId) {
        try {
            String userId = headers.get("x-user-id");
            Follow f = followService.follow(userId, userTargetId.getUserTargetId());
            return ResponseEntity.status(HttpStatus.OK).body(f);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/follow")
    public ResponseEntity<Object> unFollow(@RequestHeader Map<String, String> headers,
            @RequestBody FollowRequest userTargetId) {
        try {
            String userId = headers.get("x-user-id");
            followService.unFollow(userId, userTargetId.getUserTargetId());
            return ResponseEntity.status(HttpStatus.OK).body("Hủy theo dõi thành công");
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // @GetMapping("/test")
    // public ResponseEntity<String> testApi(@RequestHeader Map<String, String>
    // headers) {
    // String userId = headers.get("x-user-id");
    // String username = headers.get("x-username");
    // String email = headers.get("x-email");
    // String role = headers.get("x-role");
    // String response = String.format(
    // "Call API success!\nUser ID: %s\nUsername: %s\nEmail: %s\nRole: %s",
    // userId, username, email, role
    // );

    // return ResponseEntity.status(HttpStatus.OK).body(response);
    // }

//    @GetMapping("/id")
//    public ResponseEntity<Object> getUserById(@RequestBody String id) {
//        try {
//            Optional<User> u = userService.GetUserByUserId(id);
//            return ResponseEntity.status(HttpStatus.OK).body(u);
//        } catch (Exception error) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        }
//    }

    @PutMapping
    public ResponseEntity<Object> getUserById(@RequestBody UpdateUser user) {
        try {
            User u = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(u);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}