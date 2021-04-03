package com.soen390.team11.service;

import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerShippingService {

    CustomerRepository customerRepository;

    public CustomerShippingService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * create customer
     * @param customerShippingDto address information
     * @return customer id
     */
    public String createCustomer(CustomerShippingDto customerShippingDto) {
        return customerRepository.save(customerShippingDto.getCustomer()).getCustomerID();
    }
}
