package com.soen390.team11.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "part_inventory")
public class PartInventory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long materialId;

    @Column
    private Integer materialQuantity;

    public PartInventory() {

    }

    public PartInventory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

}
