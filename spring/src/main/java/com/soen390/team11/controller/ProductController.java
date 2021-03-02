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

import java.util.HashMap;
import java.util.Map;

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
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProduct(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.getAllProduct()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> retrieveProduct(@PathVariable String pid){
        String id = String.valueOf(pid);
        Product product = productService.getProductById(id);
        try {
            if (product != null) {
                return new ResponseEntity<>(objectMapper.writeValueAsString(product), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("product of the id not exist ", HttpStatus.NOT_FOUND);
            }
        }
        catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{pid}")
    public ResponseEntity<?> updateProduct(@PathVariable String pid, @RequestBody ProductRequestDto productRequestDto){
        String id = String.valueOf(pid);
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.updateProduct(id,productRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable String pid){
        String id = String.valueOf(pid);
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productService.deleteProduct(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e){

            return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/part")
    public ResponseEntity<?> getPreConfigParts(){
        try {
            return new ResponseEntity<>(productService.getAllProductPart(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
    }
}
