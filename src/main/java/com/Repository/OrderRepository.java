package com.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Orders;




@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer>{
	
	public List<Orders> findByUemail(String uemail);
	
	public List<Orders> findByBemail(String bemail);
	
	

}
