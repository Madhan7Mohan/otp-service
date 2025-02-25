package com.example.otpservice.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.otpservice.entity.OTP;
import com.example.otpservice.messaging.MessagingService;
import com.example.otpservice.repository.OTPRepository;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private MessagingService messagingService;

    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRATION_MINUTES = 5;

    public void generateAndSendOTP(String userId, String channel, String destination) {
        String otp = generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES);
        OTP otpEntity = OTP.builder()
                .userId(userId)
                .otp(otp)
                .expiryTime(expiryTime)
                .validated(false)
                .build();
        otpRepository.save(otpEntity);
        messagingService.sendOTP(destination, otp, channel);
    }

    public boolean validateOTP(String userId, String otp) {
        Optional<OTP> otpEntity = otpRepository.findByUserIdAndOtpAndValidated(userId, otp, false);
        if (otpEntity.isPresent()) {
            OTP foundOtp = otpEntity.get();
            if (LocalDateTime.now().isBefore(foundOtp.getExpiryTime())) {
                foundOtp.setValidated(true);
                otpRepository.save(foundOtp);
                return true;
            }
        }
        return false;
    }

    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            sb.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return sb.toString();
    }
}