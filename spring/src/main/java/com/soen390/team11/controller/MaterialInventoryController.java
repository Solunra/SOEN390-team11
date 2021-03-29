package com.soen390.team11.controller;


import com.soen390.team11.service.MaterialInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the material inventory
 */
@RestController
@RequestMapping("/inventory/material")
public class MaterialInventoryController {

    MaterialInventoryService materialInventoryService;

    public MaterialInventoryController(MaterialInventoryService materialInventoryService) {
        this.materialInventoryService = materialInventoryService;
    }

    /**
     * Gets all the materials' inventory
     * @return Returns a list of all materials
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllMatInInventory(){
        return new ResponseEntity<>(materialInventoryService.getAllMaterialInventory(), HttpStatus.OK);
    }

}

