package com.soen390.team11.entity;

import javax.persistence.*;
// import java.util.List;

@Entity(name = "rawMaterial")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rawmaterialid;

    @Column
    private String name;

    @Column
    private float cost;

    /**
     * default constructor
     */
    public RawMaterial() {

    }

    /**
     * constructor with all params
     * 
     * @param rawmaterialid the rawmaterialid of the instance
     * @param name          the name of the instance
     * @param cost          the cost of the instance
     */
    public RawMaterial(String rawmaterialid, String name, float cost) {
        this.rawmaterialid = rawmaterialid;
        this.name = name;
        this.cost = cost;
    }

    /**
     * getter method for the rawmaterialid of rawmaterial
     * 
     * @return the rawmaterialid of the instance
     */
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

    /**
     * getter method for the cost of rawmaterial
     * 
     * @return the cost of the instance
     */
    public float getCost() {
        return cost;
    }

    /**
     * mutator method for the cost of rawmaterial
     * 
     * @param cost the desired cost of the instance
     */
    public void setCost(float cost) {
        this.cost = cost;
    }
}
