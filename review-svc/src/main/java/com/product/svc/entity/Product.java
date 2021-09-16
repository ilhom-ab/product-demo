package com.product.svc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_id")
	@NotNull
	private String productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "average_review_score")
	private String avrgScore;
	
	@Column(name = "number_of_reviews")
	private String numberOfReviews;
	

}
