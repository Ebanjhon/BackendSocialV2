package com.eban.AuthService.Controller;

import com.eban.AuthService.AuthConfig.JwtTokenFilter;
import com.eban.AuthService.Service.ServiceImpl.UserServiceImpl;
import com.eban.AuthService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @PostMapping("/register")
    public ResponseEntity<Object> Register(@RequestBody User user) {
        if(userService.isUserNameExist(user.getUsername()) || userService.isEmailExist(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin đăng ký không hợp lệ!");
        }
        User userCreated = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.authenticate(username, password);
        if (user.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            return ResponseEntity.status(401).body("Sai mật khẩu hoặc tài khoản!");
        }
        String token = jwtTokenFilter.generateToken(username, user.get().getRole());
        return ResponseEntity.ok(token);
    }
}
