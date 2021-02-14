package com.soen390.team11.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product_inventory")
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private int quantity;

    @Column
    private Long productid;



    public ProductInventory() {

    }

    public ProductInventory(String location, int quantity, Long productid) {
        this.location = location;
        this.quantity = quantity;
        this.productid = productid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

}
