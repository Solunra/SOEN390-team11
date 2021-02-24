package com.soen390.team11.controller;


import com.soen390.team11.service.MaterialInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/material")
public class MaterialInventoryController {
    @Autowired
    MaterialInventoryService materialInventoryService;
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllMatInInventory(){
        return new ResponseEntity<>(materialInventoryService.getAllMaterialInventory(), HttpStatus.OK);
    }

}

