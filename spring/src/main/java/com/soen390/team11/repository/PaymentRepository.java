package com.soen390.team11.repository;

import com.soen390.team11.entity.Customer;
import com.soen390.team11.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PaymentRepository extends CrudRepository<Payment, String> {
    /**
     * find payment by pay id
     * @param payid
     * @return
     */
    Optional<Payment> findByPayId(String payid);

    /**
     * delete payment by pay id
     * @param payId
     */
    @Transactional
    void deleteByPayId(String payId);
}
