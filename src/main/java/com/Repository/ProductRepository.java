package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	public List<Product> findByEmail(String email);
	
	public Product findByPid(int pid);

}
