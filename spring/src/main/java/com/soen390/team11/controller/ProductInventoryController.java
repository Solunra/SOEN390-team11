package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.service.PartService;
import com.soen390.team11.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory/products")
public class ProductInventoryController {

    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    ProductInventoryService productInventoryService;

    @Autowired
    PartService partService;

    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProductsInInventory(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.getAllProductsInInventory()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{productid}/parts")
    public ResponseEntity<?> retriveProductParts(@PathVariable Long productid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.getProductParts(productid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> createProductInventory(@RequestBody ProductInventoryRequestDto productInventoryRequestDto) {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.createProductInventory(productInventoryRequestDto)), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Product id was not found or it is already in the inventory", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductInInventory(@PathVariable Long id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.deleteProductFromInventory(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>("id of the product in the inventory does not exist", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editProductInfoInInventory(@PathVariable Long id, @RequestBody ProductInventoryRequestDto productInventoryRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.updateProductInInventory(id,productInventoryRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("product of the id not exist", HttpStatus.CONFLICT);
        }
    }
}
