package com.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.Orders;
import com.Service.OrderService;



@RestController
@CrossOrigin
public class OrdersController {
	
	@Autowired
	OrderService service;
	
	//get user orders
	@GetMapping("/getordersuser")
	public List<Orders> getorders(@RequestParam String email)
	{
		return service.getorderbyemail(email);
	}
	
	//get buser orders
	@GetMapping("/getordersbuser")
	public List<Orders> getordersbuser(@RequestParam String email)
	{
		return service.getorderbuser(email);
	}
	
	//Add orders
	@PostMapping("/addorder")
	public String addorder(@RequestBody Orders order)
	{
		return service.addorder(order);
	}
	
	@DeleteMapping("/cancleorder")
	public String ordercancle(@RequestParam int oid)
	{
		return service.deleteOrder(oid);
	}
	
	

}
