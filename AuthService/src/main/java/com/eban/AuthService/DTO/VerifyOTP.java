package com.eban.AuthService.DTO;

public class VerifyOTP {
    private String otp;

    public VerifyOTP(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
