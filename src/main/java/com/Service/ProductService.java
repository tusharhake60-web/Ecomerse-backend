package com.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Product;
import com.Repository.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	//Product Add Succefully
	public String addp(Product p)
	{
		repository.save(p);
		return "Product Add Succefully";
	}
	
	//update product
	public String updatep(int pid,Product newp)
	{
		Product exist=repository.findByPid(pid);
		if(exist!=null)
		{
			exist.setPname(newp.getPname());
			exist.setInfo(newp.getInfo());
			exist.setPrice(newp.getPrice());
			exist.setPhoto(newp.getPhoto());
			exist.setCatageroy(newp.getCatageroy());
			
			repository.save(exist);
			return "Product Update Succefully";
		}
		return "Product Not Found";
	}
	
	//delete product
	public String deletep(int pid)
	{
		repository.deleteById(pid);
		return "recorde delete succefully";
	}
	
	//get all product
	public List<Product> getallp()
	{
		return repository.findAll();
	}
	
	//getproduct by email
	public List<Product> getpbyemail(String email)
	{
		return repository.findByEmail(email);
	}

}
