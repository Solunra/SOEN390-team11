package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.service.ProductMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the product machinery
 */
@RestController
@RequestMapping("/machinery")
public class ProductMachineryController {

    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    private ProductMachineryService productMachineryService;

    /**
     * Retrieves all product machinery
     *
     * @return List of all product machinery
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllMachineries() {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productMachineryService.getAllMachineriesWithDto()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Creates a new product machinery
     *
     * @param productMachineryDto Request body with the new Product Machinery
     * @return The ID of the new product machinery
     */
    @PutMapping
    public ResponseEntity<?> createMachinery(@RequestBody ProductMachineryDto productMachineryDto) {
        try {
            String machineryId = productMachineryService.createMachinery(productMachineryDto);
            return new ResponseEntity<>(objectMapper.writeValueAsString(machineryId), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Updates a specified product machinery
     *
     * @param machineryId The product machinery's ID
     * @param op The Operation to do on the product machinery
     * @return A success message
     */
    @PostMapping("/{machineryId}/{op}")
    public ResponseEntity<?> updateMachineryStatus(@PathVariable String machineryId, @PathVariable String op) {
        try {
            String result = productMachineryService.updateMachineryStatus(machineryId, op);
            // try to find the machinery and update its machinery status
            if (result != null && result.equals("Success"))
                return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.OK);

            return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Attempt to associate a machinery with a product to produce.
     *
     * It first tries to find an UNASSIGNED machinery, then tries to set the product of the machinery.
     *
     * @param productId The product's ID
     * @return A success message
     */
    @PostMapping("/product/{productId}")
    public ResponseEntity<?> attemptProduceProduct(@PathVariable String productId) {
        System.out.println("attemp product");
        try {
            // try to find an unassigned machinery and occupy it
            String result = productMachineryService.occupyMachinery(
                productMachineryService.findAvailableMachinery(), productId);

            if (result != null && result.equals("Success"))
                return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.OK);

            return new ResponseEntity<>(objectMapper.writeValueAsString("Not Success"), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }

    }

}
