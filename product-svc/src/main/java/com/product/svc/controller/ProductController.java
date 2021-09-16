package com.product.svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.svc.dto.ProductDto;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductController {
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${review.api.url}")
	private String apiUrl;

	@GetMapping("/product/{productId}")
	@ApiOperation(value = "Get the product by Product Id")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "productId") String productId) {
		return new ResponseEntity<>(restTemplate.getForObject(apiUrl + productId, ProductDto.class), HttpStatus.OK);
	}
}
