package com.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Entity.User;
import com.Repository.UserRepository;
import com.UserDTO.UserDto;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	// user register
	public String userregister(User u) {
		User exist = repository.findByEmail(u.getEmail());
		if (exist == null) {
			repository.save(u);

			Map<String, String> payload = new HashMap<>();
			payload.put("name", u.getFname());
			payload.put("email", u.getEmail());
			payload.put("lname", u.getLname());
			payload.put("utype", u.getUtype());

			// Webhook call
			RestTemplate restTemplate = new RestTemplate();
			String webhookUrl = "https://autoworkflo.app.n8n.cloud/webhook/076899c7-5ff0-43ac-ba68-cbee42845b20";
			restTemplate.postForObject(webhookUrl, payload, String.class);

			return "User Register Succefully";

		} else {
			return "this user already registed use another email";
		}
	}

	// login
	public User loginuser(UserDto dto) {
		User existuser = repository.findByEmail(dto.getEmail());
		if (existuser != null) {
			if (dto.getPassword().equals(existuser.getPassword())) {
				return existuser;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
