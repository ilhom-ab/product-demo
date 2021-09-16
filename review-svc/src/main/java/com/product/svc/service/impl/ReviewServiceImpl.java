package com.product.svc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.svc.dao.ProductRepository;
import com.product.svc.dto.ProductDto;
import com.product.svc.entity.Product;
import com.product.svc.service.ReviewService;
import com.product.svc.util.CommonUtil;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto> productResList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		if (!products.isEmpty()) {
			products.forEach((product) -> {
				ProductDto dto = CommonUtil.mapToProductDtoResponse(product);
				productResList.add(dto);
			});
		} else {
			throw new NoResultException("Products not found");
		}
		return productResList;
	}

	@Override
	public ProductDto getByProductId(String productId) throws NoResultException {
		Optional<Product> product = productRepository.findByProductId(productId);
		if (!product.isPresent()) {
			throw new NoResultException("Product not found");
		}

		return CommonUtil.mapToProductDtoResponse(product.get());
	}

	@Override
	public ProductDto saveProduct(ProductDto product) {

		if (null == product.getProductId() || product.getProductId().isEmpty()) {
			product.setProductId(CommonUtil.getRandomString());
		}

		Product pord = CommonUtil.mapToProduct(product);
		return CommonUtil.mapToProductDtoResponse(productRepository.saveAndFlush(pord));
	}

	@Override
	public ProductDto updateProduct(ProductDto product, String productId) throws NoResultException {
		Optional<Product> existingProduct = productRepository.findByProductId(productId);
		if (!existingProduct.isPresent()) {
			throw new NoResultException("Product not found");
		}

		Product pord = existingProduct.get();
		pord.setProductName(product.getProductName());
		pord.setProductId(product.getProductId());
		pord.setAvrgScore(product.getAvrgScore());
		pord.setNumberOfReviews(product.getNumberOfReviews());

		return CommonUtil.mapToProductDtoResponse(productRepository.save(pord));
	}

	@Override
	public void deleteProduct(String productId) {
		Optional<Product> product = productRepository.findByProductId(productId);
		if (!product.isPresent()) {
			throw new NoResultException("Product not found");
		}

		productRepository.deleteById(product.get().getId());
	}

}
