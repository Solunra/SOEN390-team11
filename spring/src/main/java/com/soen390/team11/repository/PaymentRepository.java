package com.soen390.team11.repository;

import com.soen390.team11.entity.Customer;
import com.soen390.team11.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, String> {
    Optional<Payment> findByPayId(String payid);
    @Transactional
    void deleteByPayId(String payId);
}
