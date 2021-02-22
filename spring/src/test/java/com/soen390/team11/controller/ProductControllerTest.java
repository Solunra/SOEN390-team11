package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {
    @Autowired
    ProductController productController;
    ProductRequestDto productRequestDto;
    Product createdProduct;
    ObjectMapper objectMapper= new ObjectMapper();
    @BeforeAll
    public void setup()
    {
        productRequestDto = new ProductRequestDto("bike","mountain","medium","black","matte","A");
    }
    @Test
    @Order(1)
    void createProduct() throws Exception{
        ResponseEntity<?> responseEntity = productController.createProduct(productRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Map<String,Object> responseBody = objectMapper.readValue(responseEntity.getBody().toString(),Map.class);
        createdProduct = new Product(responseBody.get("name").toString(),responseBody.get("type").toString(),responseBody.get("size").toString(), responseBody.get("color").toString(),responseBody.get("finish").toString(),responseBody.get("grade").toString());
        createdProduct.setProductid(Long.valueOf(responseBody.get("productid").toString()));
    }

    @Test
    @Order(2)
    void retrieveAllProduct() {
        ResponseEntity<?> responseEntity = productController.retrieveAllProduct();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Order(3)
    void retrieveProduct() {
        ResponseEntity<?> responseEntity = productController.retrieveProduct(createdProduct.getProductid().toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(4)
    void updateProduct() {
        ProductRequestDto newProduct = new ProductRequestDto("bike1","mountain1","medium","black","matte","A");
        ResponseEntity<?> responseEntity = productController.updateProduct(createdProduct.getProductid().toString(), newProduct);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(5)
    void deleteProduct() {
        ResponseEntity<?> responseEntity = productController.deleteProduct(createdProduct.getProductid().toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = productController.deleteProduct(Long.valueOf("1000").toString());
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }


}