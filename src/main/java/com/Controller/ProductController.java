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

import com.Entity.Product;
import com.Service.ProductService;



@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductService service;
	
	//add product
	@PostMapping("/addproduct")
	public String addp(@RequestBody Product p)
	{
		return service.addp(p);
	}
	
	//update product
	@PostMapping("/updateproduct")
	public String updatep(@RequestParam int pid, @RequestBody Product p)
	{
		return service.updatep(pid ,p);
		
	}
	
	//delete product
	@DeleteMapping("/deleteproduct")
	public String deletep(@RequestParam int pid)
	{
		return service.deletep(pid);
	}
	
	//get all product
	@GetMapping("/getallproduct")
	public List<Product> findallp()
	{
		return service.getallp();
	}
	
	//get product by email
	@GetMapping("/findpbyemail")
	public List<Product> findpbyemail(@RequestParam String email)
	{
		return service.getpbyemail(email);
	}

}
