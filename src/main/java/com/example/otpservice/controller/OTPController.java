package com.example.otpservice.controller;

import com.example.otpservice.service.OTPService;
import com.example.otpservice.dto.OTPRequest;
import com.example.otpservice.dto.OTPVerificationRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateOTP(@RequestBody @Valid OTPRequest request) {
        try {
            otpService.generateAndSendOTP(request.getUserId(), request.getChannel(), request.getDestination());
            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to generate OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestBody @Valid OTPVerificationRequest request) {
        boolean isValid = otpService.validateOTP(request.getUserId(), request.getOtp());
        return isValid
                ? ResponseEntity.ok("OTP verified successfully.")
                : ResponseEntity.badRequest().body("Invalid OTP.");
    }
}
