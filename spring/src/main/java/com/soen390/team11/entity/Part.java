package com.soen390.team11.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Part
 */
@Entity(name = "part")
public class Part {

    @Id
    @GenericGenerator(name = "partid", strategy = "com.soen390.team11.generator.PartIDGenerator")
    @GeneratedValue(generator = "partid")
    private String partid;

    @Column
    private String name;

    @OneToOne(mappedBy = "correspondingPart")
    private PartInventory partInventory;

    /**
     * default constructor
     */
    public Part() {
    }

    /**
     * constructor with all params
     * 
     * @param partid the desired partid of this instance
     * @param name   the desired name of this instance
     */
    public Part(String partid, String name) {
        this.partid = partid;
        this.name = name;
    }

    /**
     * getter method for the part partid
     * 
     * @return the partid of the instance
     */
    public String getPartid() {
        return partid;
    }

    /**
     * mutator method for the part partid
     * 
     * @param partid the desired partid of this instance
     */
    public void setPartid(String partid) {
        this.partid = partid;
    }

    /**
     * getter method for the part name
     * 
     * @return the name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * mutator method for the part name
     * 
     * @param name the desired name of this instance
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method for the part inventory
     *
     * @return the inventory of the corresponding part
     */
    public PartInventory getPartInventory() {
        return partInventory;
    }

    /**
     * mutator method for the part inventory
     *
     * @param partInventory the inventory to set to
     */
    public void setPartInventory(PartInventory partInventory) {
        this.partInventory = partInventory;
    }
}