package com.Controller;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Service.OtpService;

@RestController
@CrossOrigin
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {
        return otpService.sendOtp(email);
    }

    @PostMapping("/verify")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {

        boolean valid = otpService.verifyOtp(email, otp);

        return valid ? "OTP Verified Successfully"
                : "Invalid OTP";
    }

    @GetMapping("/test")
    public String test() {
        try {
            InetAddress address = InetAddress.getByName("smtp.gmail.com");
            return address.getHostAddress();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/smtp-test")
    public String smtpTest() {
        try (Socket socket = new Socket()) {
            socket.connect(
                    new InetSocketAddress("smtp.gmail.com", 587),
                    10000);
            return "SMTP Port Reachable";
        } catch (Exception e) {
            return e.toString();
        }
    }
}