package com.soen390.team11.repository;

import com.soen390.team11.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    /**
     * find Customer by customer id
     * @param customerId
     * @return
     */
    Optional<Customer> findByCustomerID(String customerId);

    /**
     * delete by customer id
     * @param customerid
     */
    @Transactional
    void deleteByCustomerID(String customerid);
}
