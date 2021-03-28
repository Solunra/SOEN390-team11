package com.soen390.team11.repository;

import com.soen390.team11.entity.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
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
}
