package com.eban.AuthService.Controller;

import com.eban.AuthService.AuthConfig.JwtTokenFilter;
import com.eban.AuthService.DTO.LoginRsp;
import com.eban.AuthService.DTO.TokenResponse;
import com.eban.AuthService.DTO.VerifyOTP;
import com.eban.AuthService.Service.ServiceImpl.ActiveUserService;
import com.eban.AuthService.Service.ServiceImpl.MailService;
import com.eban.AuthService.Service.ServiceImpl.OTPService;
import com.eban.AuthService.Service.ServiceImpl.UserServiceImpl;
import com.eban.AuthService.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private OTPService otpService;
    @Autowired
    private ActiveUserService activeUserService;

    @GetMapping
    public ResponseEntity<Object> getUser(@RequestParam String username) {
        Optional<User> u = userService.GetUserByUserName(username);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> Register(@RequestBody User user) {
        if (userService.isUserNameExist(user.getUsername()) || userService.isEmailExist(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin đăng ký không hợp lệ!");
        }
        User userCreated = userService.saveUser(user);
        if (userCreated == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khi tạo tài khoản!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userCreated.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRsp data) {
        Optional<User> user = userService.authenticate(data.getUsername(), data.getPassword());
        if (user.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            return ResponseEntity.status(401).body("Mật khẩu hoặc tài khoản không chính xác!");
        }
        String t = jwtTokenFilter.generateToken(data.getUsername(), user.get().getRole());
        TokenResponse token = new TokenResponse(t);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Token không hợp lệ hoặc thiếu token!");
        }
        String token = authHeader.substring(7);
        try {
            String result = jwtTokenFilter.validateToken(token);
            Optional<User> user = userService.GetUserByUserName(result);
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateOTP(@RequestBody String userId) {
        try {
            String otp = otpService.saveOTP(userId);
            return ResponseEntity.status(HttpStatus.OK).body("OTP: " + otp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody VerifyOTP data) {
        Boolean result = otpService.getOTP(data);
        if (result) {
            otpService.deleteOTP(data.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body("Auth OTP Successful!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("OTP doesn't match!");
        }
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/test-redis")
    public String testRedisConnection() {
        try {
            String response = redisTemplate.getConnectionFactory().getConnection().ping();
            if ("PONG".equals(response)) {
                return "Kết nối Redis thành công!";
            } else {
                return "Lỗi kết nối Redis: " + response;
            }
        } catch (Exception e) {
            return "Không thể kết nối Redis: " + e;
        }
    }

//    test email
    @Autowired
    private MailService mailService;

    @GetMapping("/send-mail")
    public ResponseEntity<String> testSendEmail(){
        try{
            String otp = "123456";
            mailService.sendOTP("ebanjhony202@gmail.com", "Xác nhận mã OTP", otp);
            return ResponseEntity.status(HttpStatus.OK).body(otp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/active-user")
    public ResponseEntity<String> activeUserAccount(){
        try{
            Boolean result = activeUserService.activeAccountUser("123456789");
            return ResponseEntity.status(HttpStatus.OK).body("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail!");
        }
    }
}
