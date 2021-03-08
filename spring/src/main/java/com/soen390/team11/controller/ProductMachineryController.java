package com.soen390.team11.controller;

import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.service.ProductMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
