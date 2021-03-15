package com.soen390.team11.repository;

import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.CustomerPurchaseId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerPurchaseRepository extends CrudRepository<CustomerPurchase, CustomerPurchaseId> {
    Optional<CustomerPurchase> findByCustomerPurchaseIdCustomerID(String customerId);
    Optional<CustomerPurchase> findByCustomerPurchaseIdProductID(String productID);
    Optional<CustomerPurchase> findByCustomerPurchaseIdInvoiceID(String invoiceID);
    List<CustomerPurchase> findAllByCustomerPurchaseIdInvoiceID(String invoiceID);


}
