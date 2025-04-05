package com.eban.AuthService.Service.ServiceImpl;

import com.eban.AuthService.DTO.VerifyOTP;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
public class OTPService {
    private final StringRedisTemplate redisTemplate;
    private static final long OTP_TIME = 2;// OTP 2 phut

    public OTPService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // save OTP to redis
    public String saveOTP(String userId) {
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(999999));
        String key = "OTP_" + userId;
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(OTP_TIME));
        System.out.println("create" + otp);
        return otp;
    }

    // get OTP
    public Boolean getOTP(String otpVerify, String userId) {
        String otp = redisTemplate.opsForValue().get("OTP_" + userId);

        System.out.println(
                "Stored OTP: " + otp + " (Type: " + (otp != null ? otp.getClass().getSimpleName() : "null") + ")");
        System.out.println("Input OTP: " + otpVerify + " (Type: "
                + (otpVerify != null ? otpVerify.getClass().getSimpleName() : "null") + ")");

        return otp.equals(otpVerify);
    }

    // delete Otp
    public void deleteOTP(String userId) {
        redisTemplate.delete("OTP_" + userId);
    }
}
