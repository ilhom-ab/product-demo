package com.product.svc.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.svc.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer> {

	public Optional<Product> findByProductId(String productId);
	
}
