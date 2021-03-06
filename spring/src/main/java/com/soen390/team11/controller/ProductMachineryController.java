package com.soen390.team11.controller;

import com.soen390.team11.service.ProductMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/machinery")
public class ProductMachineryController {

    @Autowired
    private ProductMachineryService productMachineryService;

    @GetMapping
    public ResponseEntity<?> getAllMachineries() {
        return ResponseEntity.ok(productMachineryService.getAllMachineries());
    }

}
