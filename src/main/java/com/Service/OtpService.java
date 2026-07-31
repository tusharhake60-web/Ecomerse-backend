package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.Entity.UserOtp;
import com.Repository.UserOtpRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserOtpRepository repository;

    public String generateOtp() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public String sendOtp(String email) {

        String otp = generateOtp();

        Optional<UserOtp> existing = repository.findByEmail(email);

        if (existing.isPresent()) {
            UserOtp userOtp = existing.get();
            userOtp.setOtp(otp);
            repository.save(userOtp);
        } else {
            repository.save(new UserOtp(email, otp));
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);

        try {
        mailSender.send(message);

        return "OTP Sent Successfully";
        }
        catch(Exception e)
        {
        	 e.printStackTrace();
        	    return "Failed to send OTP: " + e.getMessage();
        }
    }

    public boolean verifyOtp(String email, String otp) {

        Optional<UserOtp> userOtp = repository.findByEmail(email);

        return userOtp.isPresent() &&
                userOtp.get().getOtp().equals(otp);
    }
}