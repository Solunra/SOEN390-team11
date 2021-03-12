package com.soen390.team11.repository;

import com.soen390.team11.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findByCustomerID(String customerId);
}
