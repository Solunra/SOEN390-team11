package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    ObjectMapper objectMapper= new ObjectMapper();
    @Autowired
    ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.createProduct(productRequestDto)), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("create unsuccess", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProduct(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.getAllProduct()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("get unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.getProductById(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("get unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.updateProduct(id,productRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("update unsuccessful", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.deleteProduct(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("delete unsuccessful", HttpStatus.CONFLICT);
        }
    }


}
