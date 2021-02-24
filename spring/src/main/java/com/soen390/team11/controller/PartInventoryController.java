package com.soen390.team11.controller;

import com.soen390.team11.service.PartInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/part")
public class PartInventoryController {
    @Autowired
    PartInventoryService partInventoryService;
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllPartInInventory(){
        return new ResponseEntity<>(partInventoryService.getAllPartInventory(), HttpStatus.OK);
    }
}
