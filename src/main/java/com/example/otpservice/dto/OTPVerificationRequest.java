package com.example.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class OTPVerificationRequest {
    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "OTP is required")
    private String otp;
}
