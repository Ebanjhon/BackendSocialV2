package com.eban.UserService.Controller;

import com.eban.UserService.DTO.AuthReq;
import com.eban.UserService.DTO.UserReq;
import com.eban.UserService.Model.User;
import com.eban.UserService.Service.ServiceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserReq user) {
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
    public ResponseEntity<Object> getUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thiếu token hoặc không hợp lệ");
        }
        token = token.substring(7);
        Optional<User> user = userService.GetUserProfile(token);
        if(user.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy user!");
        }
    }
}