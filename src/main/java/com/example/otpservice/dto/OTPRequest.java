package com.example.otpservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class OTPRequest {
    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Channel is required (SMS/Email)")
    private String channel;

    @NotBlank(message = "Destination (email/phone) is required")
    private String destination;
}
