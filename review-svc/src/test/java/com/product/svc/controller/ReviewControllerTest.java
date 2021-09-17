package com.product.svc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.svc.dao.ProductRepository;
import com.product.svc.dto.ProductDto;
import com.product.svc.service.ReviewService;


@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

	@MockBean
    private ReviewService reviewService;
	
    @MockBean
    private ProductRepository productRepository;
    
    @Autowired
    private WebApplicationContext applicationContext;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
    
    @Test
    void getProducts_ReturnOk() throws Exception {
        when(reviewService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/review").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
    @Test
    void getProductById_ReturnOk() throws Exception {
        when(reviewService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/review/{productId}", "HP2007").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
    @Test
    void saveProduc_ReturnCreated_IfValidRequest() throws Exception {
    	ProductDto product = new ProductDto();
    	product.setProductId("HP2007");
    	product.setProductName("Test book");
    	
        mockMvc.perform(MockMvcRequestBuilders
            .post("/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)))
            .andExpect(status().isCreated());
        verify(reviewService, times(1)).saveProduct(any());
    }
    
    @Test
    void updateProduct_ReturnOk_IfValidRequest() throws Exception {
    	ProductDto product = new ProductDto();
    	product.setProductId("HP2007");
    	product.setProductName("Test book");
    	
        mockMvc.perform(MockMvcRequestBuilders
            .put("/review/HP2007")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)))
            .andExpect(status().isOk());
        verify(reviewService, times(1)).updateProduct(any(), any());
    }
    
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
