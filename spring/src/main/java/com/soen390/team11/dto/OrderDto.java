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
    private String userid;
    private String username;
    private String orderDate;

    public OrderDto(String vendorID, String saleID, int quantity, OffsetDateTime dateTime, String userid, String username, String orderDate) {
        this.vendorID = vendorID;
        this.saleID = saleID;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.userid = userid;
        this.username = username;
        this.orderDate = orderDate;
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

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
