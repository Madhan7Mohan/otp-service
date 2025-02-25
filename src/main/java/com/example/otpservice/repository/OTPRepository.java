package com.example.otpservice.repository;

import com.example.otpservice.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByUserIdAndOtpAndValidated(String userId, String otp, boolean validated);
}