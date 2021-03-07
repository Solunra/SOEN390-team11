package com.soen390.team11.dto;

public class ProductMachineryDto {

    private String name;
    private String status;
    private int timer;
    private String productId;

    public ProductMachineryDto(String name, String status, int timer, String productId) {
        this.name = name;
        this.status = status;
        this.timer = timer;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getTimer() {
        return timer;
    }

    public String getProductId() {
        return productId;
    }
}
