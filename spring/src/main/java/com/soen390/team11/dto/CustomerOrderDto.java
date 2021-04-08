package com.soen390.team11.dto;

import java.time.OffsetDateTime;

/**
 * information of the order done by the customer
 */
public class CustomerOrderDto {
    private String description;
    private String purchaseDate;
    private String deliveryDate;
    private String cost;
    private String quantity;

    public CustomerOrderDto(String description, OffsetDateTime purchaseDate, OffsetDateTime deliveryDate, float cost, Integer quantity) {
        this.description = description;
        this.purchaseDate = purchaseDate.toLocalDate().toString()+ " at "+purchaseDate.toLocalTime().toString();
        this.deliveryDate = deliveryDate == null?"undefined":deliveryDate.toLocalDate().toString()+ " at "+deliveryDate.toLocalTime().toString();;
        this.cost = "$"+cost;
        this.quantity = quantity.toString();
    }

    public String getDescription() {
        return description;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getCost() {
        return cost;
    }

    public String getQuantity() {
        return quantity;
    }
}
