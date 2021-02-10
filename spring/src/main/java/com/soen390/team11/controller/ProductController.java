package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    ObjectMapper objectMapper= new ObjectMapper();

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString("create"), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("create unsuccess", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProduct(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString("get all product"), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("get unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString("get specific product"), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("get unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString("create"), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("update unsuccessful", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString("delete"), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("delete unsuccessful", HttpStatus.CONFLICT);
        }
    }


}
