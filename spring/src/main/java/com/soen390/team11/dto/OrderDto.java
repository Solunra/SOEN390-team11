package com.soen390.team11.dto;

import java.time.OffsetDateTime;

/**
 * Request body for Orders
 */
public class OrderDto {

    private String vendorID;
    private String saleID;
    private int quantity;
    private OffsetDateTime dateTime;

    public OrderDto(String vendorID, String saleID,int quantity, OffsetDateTime dateTime) {
        this.vendorID = vendorID;
        this.saleID = saleID;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public String getVendorID() {
        return vendorID;
    }

    public String getSaleID() {
        return saleID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
