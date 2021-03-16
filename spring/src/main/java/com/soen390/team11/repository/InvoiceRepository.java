package com.soen390.team11.repository;

import com.soen390.team11.entity.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String > {
    Optional<Invoice> findByInvoiceID(String invoiceId);
    @Transactional
    void deleteByInvoiceID(String invoiceId);
}
