package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


class ProductControllerTest {

    ProductController productController;
    @Mock
    ProductService productService;
    ProductRequestDto productRequestDto;
    Product createdProduct;
    String productId;
    ObjectMapper objectMapper= new ObjectMapper();

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        productController = new ProductController(productService);
        productRequestDto = new ProductRequestDto("bike","mountain","medium","black","matte","A",0,0);
        productId = "productID";
        createdProduct = new Product(productRequestDto.getName(),productRequestDto.getType(), productRequestDto.getSize(), productRequestDto.getColor(),
                productRequestDto.getFinish(), productRequestDto.getGrade(), productRequestDto.getCost(), productRequestDto.getPrice());
        createdProduct.setProductid(productId);
    }

    @Test
    void createProduct() {
        when(productService.createProduct(productRequestDto)).thenReturn(createdProduct);
        ResponseEntity<?> responseEntity = productController.createProduct(productRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void retrieveAllProduct() {
        when(productService.getAllProduct()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = productController.retrieveAllProduct();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void retrieveProduct() {
        when(productService.getProductById(productId)).thenReturn(createdProduct);
        ResponseEntity<?> responseEntity = productController.retrieveProduct(createdProduct.getProductid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateProduct() throws Exception {
        ProductRequestDto newProduct = new ProductRequestDto("bike1","mountain1","medium","black","matte","A",0,0);

        when(productService.updateProduct(productId, newProduct)).thenReturn(new Product("bike1","mountain1","medium","black","matte","A",0,0));
        ResponseEntity<?> responseEntity = productController.updateProduct(createdProduct.getProductid(), newProduct);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteProduct() throws Exception {
        when(productService.deleteProduct(productId)).thenReturn("success");
        ResponseEntity<?> responseEntity = productController.deleteProduct(createdProduct.getProductid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        when(productService.deleteProduct("1000")).thenThrow(new Exception());
        responseEntity = productController.deleteProduct("1000");
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void getPreConfigParts() {
        when(productService.getAllProductPart()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = productController.getPreConfigParts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}