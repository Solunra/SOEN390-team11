package com.soen390.team11.dto;

/**
 * Product Machinery Request Body
 */
public class ProductMachineryDto {

    private String name;
    private String status;
    private int timer;
    private String productId;
    private String productname;
    private String macId;

    public ProductMachineryDto() {
    }

    public ProductMachineryDto(String name, String status, int timer, String productId) {
        this.name = name;
        this.status = status;
        this.timer = timer;
        this.productId = productId;
    }

    public ProductMachineryDto(String macId, String name, String status, int timer, String productId, String productname) {
        this.macId=macId;
        this.name = name;
        this.status = status;
        this.timer = timer;
        this.productId = productId;
        this.productname = productname;
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

    public String getProductname() {
        return productname;
    }

    public String getMacId() {
        return macId;
    }
}
