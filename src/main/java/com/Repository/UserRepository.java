package com.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//find by email
	public User findByEmail(String email);

}
