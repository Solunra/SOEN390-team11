package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Raw Material
 */
@Entity(name="rawmaterial")
public class RawMaterial {

    @Id
    @GenericGenerator(name="rawmaterialid", strategy = "com.soen390.team11.generator.RawMaterialIDGenerator")
    @GeneratedValue(generator="rawmaterialid")
    private String rawmaterialid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private String unit;

    public RawMaterial() {

    }

    public RawMaterial(String rawmaterialid, String name) {
        this.rawmaterialid = rawmaterialid;
        this.name = name;
    }

    public RawMaterial(String name, String description, double price, String unit) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public String getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(String rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
