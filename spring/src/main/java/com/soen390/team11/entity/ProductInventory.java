package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Product Inventory
 */
@Entity(name = "product_inventory")
public class ProductInventory {

    @Id
    @GenericGenerator(name="id", strategy = "com.soen390.team11.generator.ProductInventoryIDGenerator")
    @GeneratedValue(generator="id")
    private String id;

    @Column
    private String location;

    @Column
    private int quantity;

    @Column
    private String productid;



    public ProductInventory() {

    }

    public ProductInventory(String location, int quantity, String productid) {
        this.location = location;
        this.quantity = quantity;
        this.productid = productid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

}
