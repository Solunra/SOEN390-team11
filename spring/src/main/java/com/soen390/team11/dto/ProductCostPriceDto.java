package com.soen390.team11.dto;

public class ProductCostPriceDto {
    private String proid;
    private String description;
    private String cost;
    private String price;

    public ProductCostPriceDto(String proid, String description, float cost, float price) {
        this.proid = proid;
        this.description = description;
        this.cost = "$ "+cost;
        this.price = "$ "+ price;
    }

    public String getProid() {
        return proid;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }

    public String getPrice() {
        return price;
    }
}
