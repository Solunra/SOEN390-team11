package com.soen390.team11.repository;

import com.soen390.team11.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, String > {
    Optional<Invoice> findByInvoiceID(String invoiceId);
}
