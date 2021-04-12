package com.soen390.team11.repository;

import com.soen390.team11.entity.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String > {
    /**
     * find by Invoice Id
     * @param invoiceId
     * @return
     */
    Optional<Invoice> findByInvoiceID(String invoiceId);

    /**
     * delete by Invoice Id
     * @param invoiceId
     */
    @Transactional
    void deleteByInvoiceID(String invoiceId);

    /**
     * find all purchase between date, not inlude the border
     * @param startDate
     * @param endDate
     * @return
     */
    List<Invoice> findAllByPurchasedateBetween(OffsetDateTime startDate, OffsetDateTime endDate);

    /**
     * monthly report of customer purchase
     * @return
     */
    @Query(
            value = "SELECT EXTRACT(MONTH FROM i.purchasedate) as month, SUM(paymentamount) AS PRICE FROM invoice i GROUP BY month",
            nativeQuery = true)
    List<Map<String, Object>> groupByMonth();

    /**
     * average monthly customer purchase
     * @return
     */
    @Query(
            value = "SELECT AVG(temp.PRICE) as average FROM (SELECT EXTRACT(MONTH FROM i.purchasedate) as month, SUM(paymentamount) AS PRICE FROM invoice i GROUP BY month) AS temp",
            nativeQuery = true)
    Map<String, Object> averageCostByMonth();

    /**
     * total income
     * @param year
     * @return
     */
    @Query(
            value = "SELECT SUM(paymentamount) as total FROM invoice i WHERE EXTRACT(YEAR FROM i.purchasedate) = :year",
            nativeQuery = true)
    Map<String, Object> totalIncome(@Param("year") Integer year);
}
