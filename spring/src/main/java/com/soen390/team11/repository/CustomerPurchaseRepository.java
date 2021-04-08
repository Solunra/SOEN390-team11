package com.soen390.team11.repository;

import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.CustomerPurchaseId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerPurchaseRepository extends CrudRepository<CustomerPurchase, CustomerPurchaseId> {
    /**
     * find customer purchase by the customer id
     * @param customerId
     * @return
     */
    Optional<CustomerPurchase> findByCustomerPurchaseIdCustomerID(String customerId);

    /**
     * find customer pruchase by invoice id
     * @param productID
     * @return
     */
    Optional<CustomerPurchase> findByCustomerPurchaseIdInvoiceID(String productID);

    /**
     * find customer purchase by invoice and product id
     * @param invoiceID
     * @param productid
     * @return
     */
    Optional<CustomerPurchase> findByCustomerPurchaseIdInvoiceIDAndCustomerPurchaseIdProductID(String invoiceID, String productid);

    /**
     * find all customer purchase by invoice id
     * @param invoiceID
     * @return
     */
    List<CustomerPurchase> findAllByCustomerPurchaseIdInvoiceID(String invoiceID);

    /**
     * find all customer purchase by userid
     * @param userId
     * @return
     */
    List<CustomerPurchase> findAllByUserid(String userId);

    /**
     * delete customer purchase
     * @param invoiceid
     */
    @Transactional
    void deleteByCustomerPurchaseIdInvoiceID(String invoiceid);
}
