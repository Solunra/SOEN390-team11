package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Part and Materials
 */
@Entity(name = "part_material")
public class PartMaterial {

    @Id
    @GenericGenerator(name="id", strategy = "com.soen390.team11.generator.PartMaterialIDGenerator")
    @GeneratedValue(generator="id")
    private String id;

    @Column
    private String partId;

    @Column
    private String materialId;

    @Column
    private Integer materialQuantity;

    public PartMaterial() {

    }

    public PartMaterial(String partId, String materialId, Integer materialQuantity) {
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
    }

    public String getPartId() {
        return partId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public String getId() {
        return id;
    }
}
