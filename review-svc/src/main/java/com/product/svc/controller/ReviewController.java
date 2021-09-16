package com.product.svc.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.svc.dto.ProductDto;
import com.product.svc.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Product related APIs")
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	@ApiOperation(value = "Get the product list")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		return new ResponseEntity<>(reviewService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	@ApiOperation(value = "Get the product by Product Id")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "productId") @NotNull String productId)
			throws NoResultException {
		return new ResponseEntity<>(reviewService.getByProductId(productId), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Save product")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto product) {
		return new ResponseEntity<>(reviewService.saveProduct(product), HttpStatus.CREATED);
	}

	@PutMapping("/{productId}")
	@ApiOperation(value = "Update product")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product,
			@PathVariable(value = "productId") @NotNull String productId) throws NoResultException {
		return new ResponseEntity<>(reviewService.updateProduct(product, productId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	@ApiOperation(value = "Delete product")
	public void deleteProduct(@PathVariable(value = "productId") @NotNull String productId) {
		reviewService.deleteProduct(productId);
	}
	

}
