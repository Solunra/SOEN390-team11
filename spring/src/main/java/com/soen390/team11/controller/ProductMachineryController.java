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

@RestController
@RequestMapping("/machinery")
public class ProductMachineryController {

    @Autowired
    private ProductMachineryService productMachineryService;

    @GetMapping
    public ResponseEntity<?> retrieveAllMachineries() {
        return ResponseEntity.ok(productMachineryService.getAllMachineries());
    }

    @PutMapping
    public ResponseEntity<?> createMachinery(@RequestBody ProductMachineryDto productMachineryDto) {
        String machineryId = productMachineryService.createMachinery(productMachineryDto);
        return ResponseEntity.ok(machineryId);
    }

    @PostMapping("/{machineryId}/{op}")
    public ResponseEntity<?> updateMachineryStatus(@PathVariable String machineryId, @PathVariable String op) {
        String result = productMachineryService.updateMachineryStatus(machineryId, op);
        // try to find the machinery and update its machinery status
        if (result != null && result.equals("Success"))
            return ResponseEntity.ok(result);

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<?> attemptProduceProduct(@PathVariable String productId) {

        // try to find an unassigned machinery and occupy it
        String result = productMachineryService.occupyMachinery(
            productMachineryService.findAvailableMachinery(), productId);

        if (result != null && result.equals("Success"))
            return ResponseEntity.ok(result);

        return ResponseEntity.badRequest().body(result);

    }

}
