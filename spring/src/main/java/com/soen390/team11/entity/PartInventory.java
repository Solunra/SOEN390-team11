package com.soen390.team11.entity;

import javax.persistence.*;

@Entity(name = "part_inventory")
public class PartInventory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long partId;

    @Column
    private Long materialId;

    @Column
    private Integer materialQuantity;

    public PartInventory() {

    }

    public PartInventory(Long partId, Long materialId, Integer materialQuantity) {
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
    }

    public Long getPartId() {
        return partId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

}
