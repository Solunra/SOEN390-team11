package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Raw Material
 */
@Entity(name = "rawmaterial")
public class RawMaterial {

    @Id
    @GenericGenerator(name = "rawmaterialid", strategy = "com.soen390.team11.generator.RawMaterialIDGenerator")
    @GeneratedValue(generator = "rawmaterialid")
    private String rawmaterialid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double cost;

    @Column
    private String unit;

    public RawMaterial() {

    }

    /**
     * constructor with all params
     * 
     * @param rawmaterialid the rawmaterialid of the instance
     * @param name          the name of the instance
     * @param cost          the cost of the instance
     */
    public RawMaterial(String rawmaterialid, String name) {
        this.rawmaterialid = rawmaterialid;
        this.name = name;
    }

    public RawMaterial(String name, String description, double cost, String unit) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.unit = unit;
    }

    public String getrawmaterialid() {
        return rawmaterialid;
    }

    /**
     * mutator method for the rawmaterialid of rawmaterial
     * 
     * @param rawmaterialid the desired rawmaterialid of the instance
     */
    public void setrawmaterialid(String rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    /**
     * getter method for the name of rawmaterial
     * 
     * @return the name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * mutator method for the name of rawmaterial
     * 
     * @param name the desired name of the instance
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double price) {
        this.cost = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}