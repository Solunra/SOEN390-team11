package com.soen390.team11.dto;

import java.time.OffsetDateTime;

/**
 * Response body for Order
 */
public class OrderResponseDto {
    private String vendorname;
    private String type;
    private String rawname;
    private int quantity;
    private String status;
    private String vendorID;
    private String rawID;
    private String orderDateTime;
    private String username;
    private String userid;
    private String amount;
    private String deliveryDateTime;

    public OrderResponseDto() {
    }

    public OrderResponseDto(String vendorname, String type, String rawname, int quantity, String status, String vendorID, String rawID, OffsetDateTime orderDateTime, String username, String userid, Double amount, OffsetDateTime deliveryDateTime) {
        this.vendorname = vendorname;
        this.type = type;
        this.rawname = rawname;
        this.quantity = quantity;
        this.status = status;
        this.vendorID = vendorID;
        this.rawID = rawID;
        this.orderDateTime = orderDateTime.toLocalDate().toString();
        this.username = username;
        this.userid = userid;
        this.amount= "$ "+ amount;
        this.deliveryDateTime = deliveryDateTime.toLocalDate().toString()+ " at "+deliveryDateTime.toLocalTime().toString();
    }

    public String getVendorname() {
        return vendorname;
    }

    public String getType() {
        return type;
    }

    public String getRawname() {
        return rawname;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getVendorID() {
        return vendorID;
    }

    public String getRawID() {
        return rawID;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public String getAmount() {
        return amount;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }
}
