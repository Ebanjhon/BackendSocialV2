package com.eban.UserService.Controller;

import com.eban.UserService.DTO.UserRsp;
import com.eban.UserService.Model.User;
import com.eban.UserService.Service.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserRsp user) {
        if(userService.isUserNameExist(user.getUsername()) || userService.isEmailExist(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tên người dùng và email đã có sẳn!");
        }
        User userCreated = userService.saveUser(user);
        if (userCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hiện tại không thể tạo tài khoản người dùng!");
    }

    @GetMapping
    public ResponseEntity<Object> getUserProfile(@RequestParam String username) {
        Optional<User> user = userService.GetUserByUserName(username);
        if(user.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy user!");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testApi(@RequestHeader Map<String, String> headers) {
        String userId = headers.get("x-user-id");
        String username = headers.get("x-username");
        String email = headers.get("x-email");
        String role = headers.get("x-role");
        String response = String.format(
                "Call API success!\nUser ID: %s\nUsername: %s\nEmail: %s\nRole: %s",
                userId, username, email, role
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}