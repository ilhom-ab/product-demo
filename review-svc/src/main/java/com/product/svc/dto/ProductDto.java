package com.product.svc.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
	private Integer id;
    @Size(max = 6)
	private String productId;
    @Size(max = 50)
	private String productName;
    @Size(max = 6)
	private Integer avrgScore;
	private Integer numberOfReviews;
}
