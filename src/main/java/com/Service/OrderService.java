package com.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Entity.Orders;
import com.Repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;

	// get user orders
	public List<Orders> getorderbyemail(String uemail) {
		return repository.findByUemail(uemail);
	}

	// get buser order
	public List<Orders> getorderbuser(String bemail) {
		return repository.findByBemail(bemail);
	}

	// Add order
	public String addorder(Orders o) {
		repository.save(o);
		Map<String, Object> payload = new HashMap<>();
		payload.put("OrderId", o.getOid());
		payload.put("Address", o.getAddress());
		payload.put("Mobile", o.getMobile());
		payload.put("Pincode", o.getPincode());
		payload.put("Name", o.getName());
		payload.put("Uemail", o.getUemail());
		payload.put("price", o.getPrice());
		payload.put("product", o.getPname());

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.postForObject("https://autoworkflo.app.n8n.cloud/webhook-test/order", payload, String.class);
		return "Order Added Succefully";
	}

	// delete order
	public String deleteOrder(int oid) {
		repository.deleteById(oid);
		return "Order Cancle Succefully";
	}

}
