package com.soen390.team11.repository;

import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.CustomerPurchaseId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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

    /**
     * get Top 5 product that customer purchase the most
     * @return
     */
    @Query(
            value = "SELECT cp.productID ,SUM(cp.amount) AS total FROM customer_purchase cp, product p WHERE cp.productID = p.productID GROUP BY cp.productID ORDER BY total DESC LIMIT 5",
            nativeQuery = true)
    List<Map<String, Object>> topProduct();

    /**
     * total customer
     * @return
     */
    @Query(
            value = "SELECT COUNT(DISTINCT cp.userid) as total FROM customer_purchase cp",
            nativeQuery = true)
    Map<String, Object> totalCustomer();

    /**
     * total product
     * @return
     */
    @Query(
            value = "SELECT SUM(cp.amount) as total FROM customer_purchase cp",
            nativeQuery = true)
    Map<String, Object> totalProduct();

    /**
     * average product by month
     * @return
     */
    @Query(
            value = "SELECT AVG(temp.total) as average FROM (SELECT EXTRACT(MONTH FROM i.purchasedate) as month, SUM(cp.amount) AS total FROM invoice i, customer_purchase cp WHERE cp.invoiceID = i.invoiceID GROUP BY month) AS temp",
            nativeQuery = true)
    Map<String, Object> monthlyAverageProduct();
}
