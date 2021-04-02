package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

/**
 * invoice of the customer purchase
 */
@Entity(name = "invoice")
public class Invoice {
    @Id
    @GenericGenerator(name="invoice_id", strategy = "com.soen390.team11.generator.InvoiceIDGenerator")
    @GeneratedValue(generator="invoice_id")
    private String invoiceID;
    @Column
    private OffsetDateTime purchasedate;
    @Column
    private Double paymentamount;

    public Invoice() {
    }

    public Invoice(OffsetDateTime purchasedate, Double paymentamount) {
        this.purchasedate = purchasedate;
        this.paymentamount = paymentamount;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public OffsetDateTime getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(OffsetDateTime purchasedate) {
        this.purchasedate = purchasedate;
    }

    public Double getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(Double paymentamount) {
        this.paymentamount = paymentamount;
    }
}
