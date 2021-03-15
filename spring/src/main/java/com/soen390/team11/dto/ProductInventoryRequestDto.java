package com.soen390.team11.dto;


import com.soen390.team11.entity.ProductInventory;

/**
 * Request body for Product Inventory
 */
public class ProductInventoryRequestDto {
    private String location;
    private int quantity;
    private String productid;

    public ProductInventoryRequestDto() {

    }

    public ProductInventoryRequestDto(String location, int quantity, String productid) {
        this.location = location;
        this.quantity = quantity;
        this.productid = productid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductid() {
        return this.productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public ProductInventory getProductInventory(){
        return new ProductInventory(location, quantity,productid);
    }
}
