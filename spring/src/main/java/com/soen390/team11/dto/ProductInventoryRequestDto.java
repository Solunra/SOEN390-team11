package com.soen390.team11.dto;


import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductInventory;

import java.util.List;

public class ProductInventoryRequestDto {
    private String location;
    private int quantity;
    private Long productid;

    public ProductInventoryRequestDto() {

    }

    public ProductInventoryRequestDto(String location, int quantity, Long productid) {
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

    public Long getProductid() {
        return this.productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public ProductInventory getProductInventory(){
        return new ProductInventory(location, quantity,productid);
    }
}
