package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.CustomizeReportDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.service.CustomerPurchaseService;
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
    CustomerPurchaseService customerPurchaseService;

    public CustomerPurchaseController(CustomerPurchaseService customerPurchaseService) {
        this.customerPurchaseService = customerPurchaseService;
    }

    /**
     * make the purchase
     * @param customerPurchaseDto make purchase using customer ID, total, and items
     * @return invoice id
     */
    @PutMapping("/purchase")
    public ResponseEntity<?> makePurchase(@RequestBody CustomerPurchaseDto customerPurchaseDto){
        return new ResponseEntity<>(customerPurchaseService.makePurchase(customerPurchaseDto), HttpStatus.CREATED);

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
    /**
     * get all order for order tab
     * @return
     */
    @GetMapping("/allOrder")
    public ResponseEntity<?> getAllOrder(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getAllOrder()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
    /**
     * get customize product
     * @return
     */
    @GetMapping("/orderAction/{productId}/{invoiceid}")
    public ResponseEntity<?> orderActions(@PathVariable String productId, @PathVariable String invoiceid){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.orderActions(productId,invoiceid)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * get all order for account tab
     * @return
     */
    @GetMapping("/account/allOrder")
    public ResponseEntity<?> getAllAccountOrder(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getAllAccountOrder()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * get report between start and end date
     * @param customizeReportDto
     * @return
     */
    @PostMapping("/report")
    public ResponseEntity<?> getReport(@RequestBody CustomizeReportDto customizeReportDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getCustomizeReport(customizeReportDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
    /**
     * get all order of logged customer
     * @return
     */
    @GetMapping("/loggedCustomer/allOrder")
    public ResponseEntity<?> getAllOrderOfLogCustomer(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPurchaseService.getAllOrderOfLogCustomer()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

}
