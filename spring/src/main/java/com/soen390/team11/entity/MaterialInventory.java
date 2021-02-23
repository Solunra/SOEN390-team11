package com.soen390.team11.entity;

import javax.persistence.*;

@Entity(name = "material_inventory")
public class MaterialInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private int quantity;

    @Column
    private Long materialid;

    public MaterialInventory() {
    }

    public MaterialInventory(String location, int quantity, Long materialid) {
        this.location = location;
        this.quantity = quantity;
        this.materialid = materialid;
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

    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }
}
