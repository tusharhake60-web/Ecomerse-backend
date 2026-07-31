package com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Orders;
import com.Repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	// get user orders
	public List<Orders> getorderbyemail(String uemail)
	{
		return repository.findByUemail(uemail);
	}
	
	//get buser order
	public List<Orders> getorderbuser(String bemail)
	{
		return repository.findByBemail(bemail);
	}
	
	//Add order
	public String addorder(Orders o)
	{
		repository.save(o);
		return "Order Added Succefully";
	}
	
	//delete order
	public String deleteOrder(int oid)
	{
		repository.deleteById(oid);
		return "Order Cancle Succefully";
	}

}
