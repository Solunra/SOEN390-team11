package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductInventoryRequestDto;
import com.soen390.team11.service.PartService;
import com.soen390.team11.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the product's inventory
 */
@RestController
@RequestMapping("/inventory/products")
public class ProductInventoryController {

    ObjectMapper objectMapper= new ObjectMapper();

    ProductInventoryService productInventoryService;
    PartService partService;

    public ProductInventoryController(ProductInventoryService productInventoryService, PartService partService) {
        this.productInventoryService = productInventoryService;
        this.partService = partService;
    }

    /**
     * Gets all products in the inventory
     *
     * @return List of all product inventory
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllProductsInInventory(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.getAllProInv()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Gets all parts of a product
     *
     * @param productid The product's ID
     * @return List of the product's parts
     */
    @GetMapping("/{productid}/parts")
    public ResponseEntity<?> retriveProductParts(@PathVariable String productid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.getProductParts(productid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }


    /**
     * Creates a new prodyct
     *
     * @param productInventoryRequestDto The request body of the new product
     * @return The new product's ID
     */
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

    /**
     * Delete the product inventory
     *
     * @param id Product's id
     * @return A response entity with status
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductInInventory(@PathVariable String id){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productInventoryService.deleteProductFromInventory(id)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>("id of the product in the inventory does not exist", HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates the Product Inventory
     *
     * @param id The product's ID
     * @param productInventoryRequestDto The request body of the new product
     * @return The product inventory details
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> editProductInfoInInventory(@PathVariable String id, @RequestBody ProductInventoryRequestDto productInventoryRequestDto){
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
