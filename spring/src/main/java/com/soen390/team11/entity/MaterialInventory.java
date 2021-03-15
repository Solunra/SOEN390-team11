package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Material Inventory
 */
@Entity(name = "material_inventory")
public class MaterialInventory {

    @Id
    @GenericGenerator(name="id", strategy = "com.soen390.team11.generator.MaterialInventoryIDGenerator")
    @GeneratedValue(generator="id")
    private String id;

    @Column
    private String location;

    @Column
    private int quantity;

    @Column
    private String materialid;

    public MaterialInventory() {
    }

    public MaterialInventory(String location, int quantity, String materialid) {
        this.location = location;
        this.quantity = quantity;
        this.materialid = materialid;
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

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }
}
