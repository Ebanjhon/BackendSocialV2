package com.eban.UserService.Controller;

import com.eban.UserService.Model.User;
import com.eban.UserService.Service.ServiceImpl.UserServiceImpl;
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

    @GetMapping
    public ResponseEntity<Object> getUserProfile(@RequestParam String username) {
        Optional<User> user = userService.GetUserByUserName(username);
        if(user.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy user:" + username);
        }

    }

    @PostMapping
    public ResponseEntity<Object> saveUserProfile(@RequestBody User user) {
        if(userService.isUserNameExist(user.getUsername()) || userService.isEmailExist(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin không hợp lệ!");
        }
        User userCreated = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

    }
}
