package com.soen390.team11.controller;

import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.service.CustomerShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for customer to
 * get product display at the shop
 * get the customize product
 * make a purchase
 * chek the status
 */
@RestController
@RequestMapping("/customer/address")
public class CustomerShippingController {

    CustomerShippingService customerShippingService;

    public CustomerShippingController(CustomerShippingService customerShippingService) {
        this.customerShippingService = customerShippingService;
    }

    /**
     * create a shipping address
     * @param customerShippingDto
     * @return
     */
    @PutMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerShippingDto customerShippingDto){
        return new ResponseEntity<>(customerShippingService.createCustomer(customerShippingDto), HttpStatus.CREATED);
    }

    /**
     * create a shipping address
     * @param customerShippingDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerShippingDto customerShippingDto){
        return new ResponseEntity<>(customerShippingService.updateCustomer(customerShippingDto), HttpStatus.OK);
    }

    /**
     * delete an address
     * @param customerID customer to be deleted
     */
    @DeleteMapping("/{customerID}")
    public void deleteCustomer(@PathVariable String customerID){
        customerShippingService.deleteCustomerById(customerID);
    }
}
