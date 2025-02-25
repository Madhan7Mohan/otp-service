package com.example.otpservice.messaging;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MessagingService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Autowired
    private JavaMailSender mailSender;

    public void sendOTP(String destination, String otp, String channel) {
        if ("email".equalsIgnoreCase(channel)) {
            sendEmail(destination, otp);
        } else if ("SMS".equalsIgnoreCase(channel)) {
            sendSms(destination, otp);
        } else {
            System.out.println("Invalid channel: " + channel);
        }
    }

    private void sendSms(String phoneNumber, String otp) {
        Twilio.init(accountSid, authToken);

        String customMessage = "Dear user, your OTP for verification is: " + otp +
                ". Please do not share this code with anyone.";

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                customMessage
        ).create();

        System.out.println("SMS sent successfully. Message SID: " + message.getSid());
    }

    private void sendEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP For Login");
        message.setText("Dear user, please use the below OTP for login.\n\nYour OTP for verification is: " + otp +
                "\n\nPlease do not share this code with anyone.\n\nBest Regards,\nThopsTech");

        mailSender.send(message);
        System.out.println("OTP email sent to: " + email);
    }
}
