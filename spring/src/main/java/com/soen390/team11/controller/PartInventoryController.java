package com.soen390.team11.controller;

import com.soen390.team11.service.PartInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Parts Inventory
 */
@RestController
@RequestMapping("/inventory/part")
public class PartInventoryController {

    PartInventoryService partInventoryService;

    public PartInventoryController(PartInventoryService partInventoryService) {
        this.partInventoryService = partInventoryService;
    }

    /**
     * Gets all parts in the inventory
     *
     * @return List of Parts Inventory
     */
    @GetMapping("/")
    public ResponseEntity<?> retrieveAllPartInInventory(){
        return new ResponseEntity<>(partInventoryService.getAllPartInventory(), HttpStatus.OK);
    }
}
