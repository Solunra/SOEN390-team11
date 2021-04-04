package com.soen390.team11.service;

import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.entity.Customer;
import com.soen390.team11.repository.CustomerRepository;
import java.util.Optional;
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

    /**
     * update customer
     * @param customerShippingDto address information
     * @return customer id
     */
    public String updateCustomer(CustomerShippingDto customerShippingDto) {
        Optional<Customer> optionalCustomer = customerRepository
            .findByCustomerID(customerShippingDto.getCustomerID());
        if (optionalCustomer.isPresent()){
            optionalCustomer.get().setFirstname(customerShippingDto.getFirstname());
            optionalCustomer.get().setLastname(customerShippingDto.getLastname());
            optionalCustomer.get().setAddress(customerShippingDto.getAddress());
            optionalCustomer.get().setCity(customerShippingDto.getCity());
            optionalCustomer.get().setProvince(customerShippingDto.getProvince());
            optionalCustomer.get().setCountry(customerShippingDto.getCountry());
            optionalCustomer.get().setZip(customerShippingDto.getZip());
        } else {
            return "Customer address is not found! ";
        }
        return customerRepository.save(optionalCustomer.get()).getCustomerID();
    }
}
