package com.soen390.team11.dto;

/**
 * top product that sale the most
 * description of product
 * quantity that it is sell
 */
public class TopProductDto {
    private String description;
    private String quantity;

    public TopProductDto(String description, String quantity) {
        this.description = description;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantity() {
        return quantity;
    }
}
