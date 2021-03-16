package com.soen390.team11.entity;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * embeddable id of customer purchase
 */
@Embeddable
public class CustomerPurchaseId implements Serializable {
    @NotNull
    private String customerID;
    @NotNull
    private String productID;
    @NotNull
    private String invoiceID;

    public CustomerPurchaseId() {
    }

    public CustomerPurchaseId(String customerID, String productID, String invoiceID) {
        this.customerID = customerID;
        this.productID = productID;
        this.invoiceID = invoiceID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerPurchaseId that = (CustomerPurchaseId) o;
        return Objects.equals(customerID, that.customerID) && Objects.equals(productID, that.productID) && Objects.equals(invoiceID, that.invoiceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, productID, invoiceID);
    }
}

