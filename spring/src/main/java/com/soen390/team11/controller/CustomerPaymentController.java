package com.soen390.team11.controller;

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

    public CustomerPaymentController(CustomerPaymentService customerPaymentService) {
        this.customerPaymentService = customerPaymentService;
    }

    /**
     * create a shipping address
     * @param customerPaymentDto
     * @return
     */
    @PutMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerPaymentDto customerPaymentDto){
        return new ResponseEntity<>(customerPaymentService.createPayment(customerPaymentDto), HttpStatus.CREATED);
    }

    /**
     * update payment information
     * @param customerPaymentDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerPaymentDto customerPaymentDto){
        return new ResponseEntity<>(customerPaymentService.updatePayment(customerPaymentDto), HttpStatus.OK);
    }

    /**
     * delete an address
     * @param paymentId customer to be deleted
     */
    @DeleteMapping("/{customerID}")
    public void deleteCustomer(@PathVariable String paymentId){
        customerPaymentService.deletePaymentById(paymentId);
    }
}
