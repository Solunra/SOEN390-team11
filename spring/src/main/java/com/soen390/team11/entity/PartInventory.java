package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity(name = "part_inventory")
public class PartInventory {

    @Id
    @GenericGenerator(name="id", strategy = "com.soen390.team11.generator.PartInventoryIDGenerator")
    @GeneratedValue(generator="id")
    private String id;

    @Column
    private String location;

    @Column
    private int quantity;

    @Column
    private String partid;

    public PartInventory() {
    }

    public PartInventory(String location, int quantity, String partid) {
        this.location = location;
        this.quantity = quantity;
        this.partid = partid;
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

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }
}
