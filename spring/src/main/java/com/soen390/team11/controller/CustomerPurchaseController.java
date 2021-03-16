package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.service.CustomerPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controller for customer to
 * get product display at the shop
 * get the customize product
 * make a purchase
 * chek the status
 */
@RestController
@RequestMapping("/customer")
public class CustomerPurchaseController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    CustomerPurchaseService customerPurchaseService;

    /**
     * make the purchase
     * @param customerPurchaseDto
     * @return
     */
    @PostMapping("/purchase/create")
    public ResponseEntity<?> makePurchase(@RequestBody CustomerPurchaseDto customerPurchaseDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.makePurchase(customerPurchaseDto)), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * check the status
     * @param invoiceid
     * @return
     */
    @GetMapping("/purchase/{invoiceid}")
    public ResponseEntity<?> getStatus(@PathVariable String invoiceid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.checkStatus(invoiceid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * get all product
     * @return
     */
    @GetMapping("/allProduct")
    public ResponseEntity<?> getAllProduct(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getAllProduct()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
    /**
     * get all product
     * @return
     */
    @GetMapping("/public/allProduct")
    public ResponseEntity<?> getAllProductPublic(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getAllProduct()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
    /**
     * get customize product
     * @param productRequestDto
     * @return
     */
    @PostMapping("/getCustomize")
    public ResponseEntity<?> getCustomizeProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getCustomerizeProduct(productRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
