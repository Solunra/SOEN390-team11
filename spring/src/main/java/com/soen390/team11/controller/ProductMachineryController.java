package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.service.ProductMachineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    private ProductMachineryService productMachineryService;

    @GetMapping
    public ResponseEntity<?> retrieveAllMachineries() {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(productMachineryService.getAllMachineries()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<?> createMachinery(@RequestBody ProductMachineryDto productMachineryDto) {
        try {
            String machineryId = productMachineryService.createMachinery(productMachineryDto);
            return new ResponseEntity<>(objectMapper.writeValueAsString(machineryId), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

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

    @PostMapping("/product/{productId}")
    public ResponseEntity<?> attemptProduceProduct(@PathVariable String productId) {

        try {
            // try to find an unassigned machinery and occupy it
            String result = productMachineryService.occupyMachinery(
                productMachineryService.findAvailableMachinery(), productId);

            if (result != null && result.equals("Success"))
                return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.OK);

            return new ResponseEntity<>(objectMapper.writeValueAsString(result), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }

    }

}
