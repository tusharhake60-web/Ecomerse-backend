package com.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    public void sendOtp(String toEmail, String otp) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        String body = """
                {
                  "sender": {
                    "name": "E-Commerce",
                    "email": "tusharhake600@gmail.com"
                  },
                  "to": [
                    {
                      "email": "%s"
                    }
                  ],
                  "subject": "OTP Verification",
                  "htmlContent": "<h2>Your OTP is: <b>%s</b></h2>"
                }
                """.formatted(toEmail, otp);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        restTemplate.exchange(
                "https://api.brevo.com/v3/smtp/email",
                HttpMethod.POST,
                entity,
                String.class);
    }
}