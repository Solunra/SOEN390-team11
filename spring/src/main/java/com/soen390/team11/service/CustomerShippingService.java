package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.dto.CustomerShippingDto;
import com.soen390.team11.entity.Customer;
import com.soen390.team11.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerShippingService {

    CustomerRepository customerRepository;
    LogService logService;

    public CustomerShippingService(CustomerRepository customerRepository, LogService logService) {
        this.customerRepository = customerRepository;
        this.logService = logService;
    }

    /**
     * create customer
     * 
     * @param customerShippingDto address information
     * @return customer id
     */
    public String createCustomer(CustomerShippingDto customerShippingDto) {
        logService.writeLog(LogTypes.USERS, "Creating customer using the customerShippingDto");
        return customerRepository.save(customerShippingDto.getCustomer()).getCustomerID();
    }

    /**
     * update customer
     * 
     * @param customerShippingDto address information
     * @return customer id
     */
    public String updateCustomer(CustomerShippingDto customerShippingDto) {
        logService.writeLog(LogTypes.USERS, "Updating customer...");
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerID(customerShippingDto.getCustomerID());
        if (optionalCustomer.isPresent()) {
            logService.writeLog(LogTypes.USERS, "Setting customer's information");
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
        logService.writeLog(LogTypes.USERS, "Saving customer's information");
        return customerRepository.save(optionalCustomer.get()).getCustomerID();
    }

    /**
     * delete customer by id
     * 
     * @param customerID
     */
    public void deleteCustomerById(String customerID) {
        logService.writeLog(LogTypes.USERS, "");
        customerRepository.deleteByCustomerID(customerID);
    }
}