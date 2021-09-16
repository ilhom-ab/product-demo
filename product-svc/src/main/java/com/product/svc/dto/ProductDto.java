package com.product.svc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
	private Integer id;
	private String productId;
	private String productName;
	private String avrgScore;
	private String numberOfReviews;
}
