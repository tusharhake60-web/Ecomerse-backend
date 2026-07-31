package com.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.User;
import com.Repository.UserRepository;
import com.UserDTO.UserDto;



@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	//user register
	public String userregister(User u)
	{
		User exist=repository.findByEmail(u.getEmail());
		if(exist==null)
		{
			repository.save(u);
			return "User Register Succefully";
		}
		else
		{
			return "this user already registed use another email";
		}
	}
	
	//login
	public User loginuser(UserDto dto)
	{
		User existuser=repository.findByEmail(dto.getEmail());
		if(existuser!=null)
		{
			if(dto.getPassword().equals(existuser.getPassword()))
			{
				return existuser;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

}
