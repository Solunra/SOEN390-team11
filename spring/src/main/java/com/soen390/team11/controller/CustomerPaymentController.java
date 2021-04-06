package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.CustomerPaymentDto;
import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.service.CustomerPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/payment")
public class CustomerPaymentController {
    CustomerPaymentService customerPaymentService;
    ObjectMapper objectMapper=new ObjectMapper();

    public CustomerPaymentController(CustomerPaymentService customerPaymentService) {
        this.customerPaymentService = customerPaymentService;
    }

    /**
     * create a shipping address
     * @param customerPaymentDto
     * @return
     */
    @PutMapping
    public ResponseEntity<?> createPayment(@RequestBody CustomerPaymentDto customerPaymentDto){
        return new ResponseEntity<>(customerPaymentService.createPayment(customerPaymentDto), HttpStatus.CREATED);
    }

    /**
     * update payment information
     * @param customerPaymentDto
     * @return
     */
    @PostMapping("/{paymentId}")
    public ResponseEntity<?> updatePayment(@PathVariable String paymentId,@RequestBody CustomerPaymentDto customerPaymentDto){
        return new ResponseEntity<>(customerPaymentService.updatePayment(customerPaymentDto,paymentId), HttpStatus.OK);
    }

    /**
     * delete an address
     * @param paymentId customer to be deleted
     */
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable String paymentId) {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(customerPaymentService.deletePaymentById(paymentId)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
