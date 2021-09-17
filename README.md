# product-demo
Spring Boot CRUD api using micro-services

Create a **demoapi** schema and **products** table on you local MySQL database.

**Execute below script:**

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(45) DEFAULT NULL,
  `average_review_score` int(1) DEFAULT NULL,
  `number_of_reviews` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

**Sample Data:**
INSERT INTO `demoapi`.`products` (`id`, `product_id`, `product_name`, `average_review_score`, `number_of_reviews`) VALUES ('1', 'HP2007', 'Harry potter', '5', '27');
INSERT INTO `demoapi`.`products` (`id`, `product_id`, `product_name`, `average_review_score`, `number_of_reviews`) VALUES ('2', 'JE2020', 'Jane Eyre', '5', '37');
INSERT INTO `demoapi`.`products` (`id`, `product_id`, `product_name`, `average_review_score`, `number_of_reviews`) VALUES ('3', 'MJ1999', 'My Journey', '4', '13');

**_NOTE:_**

Product review "review-svc" services require Basic Auth it is configured on application.properties file.


