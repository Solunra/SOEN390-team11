package com.soen390.team11.controller;

import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.service.ProductMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the product machinery
 */
@RestController
@RequestMapping("/machinery")
public class ProductMachineryController {

    @Autowired
    private ProductMachineryService productMachineryService;

    /**
     * Retrieves all product machinery
     *
     * @return List of all product machinery
     */
    @GetMapping
    public ResponseEntity<?> retrieveAllMachineries() {
        return ResponseEntity.ok(productMachineryService.getAllMachineries());
    }

    /**
     * Creates a new product machinery
     *
     * @param productMachineryDto Request body with the new Product Machinery
     * @return The ID of the new product machinery
     */
    @PutMapping
    public ResponseEntity<?> createMachinery(@RequestBody ProductMachineryDto productMachineryDto) {
        String machineryId = productMachineryService.createMachinery(productMachineryDto);
        return ResponseEntity.ok(machineryId);
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

        // try to find the machinery and update its machinery status
        if (productMachineryService.updateMachineryStatus(machineryId, op)) {
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.badRequest().body("Operation not supported");
    }

}
