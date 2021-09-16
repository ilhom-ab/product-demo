package com.product.svc.service;

import java.util.List;

import javax.persistence.NoResultException;

import com.product.svc.dto.ProductDto;

public interface ReviewService {
	
	public List<ProductDto> getAllProducts();

	public ProductDto getByProductId(String productId) throws NoResultException;
	
	public ProductDto saveProduct(ProductDto product);

	public ProductDto updateProduct(ProductDto product, String productId) throws NoResultException;

	public void deleteProduct(String productId);

}
