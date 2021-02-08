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
    //CRUD
    static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            Product product = productService.createProduct(productRequestDto);
            return new ResponseEntity<String>(mapper.writeValueAsString(product), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<String>("cannot create", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProduct() {
        try {
            return new ResponseEntity<String>(mapper.writeValueAsString(productService.retrieveAll()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            return new ResponseEntity<String>("cannot retrieve", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> retrieveByUUID(@PathVariable String uuid) {
        try {
            return new ResponseEntity<String>(mapper.writeValueAsString(productService.retrieveByUUID(uuid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            return new ResponseEntity<String>("cannot retrieve", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteByUUID(@PathVariable String uuid){
        try {
            return new ResponseEntity<String>(mapper.writeValueAsString(productService.deleteByUUID(uuid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            return new ResponseEntity<String>("cannot delete", HttpStatus.CONFLICT);
        }

    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateByUUID(@PathVariable String uuid ,@RequestBody ProductRequestDto productRequestDto){
        System.out.println("update");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new ResponseEntity<String>(mapper.writeValueAsString(productService.updateByUUID(uuid, productRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            return new ResponseEntity<String>("cannot retrieve", HttpStatus.NOT_FOUND);
        }
    }
}
