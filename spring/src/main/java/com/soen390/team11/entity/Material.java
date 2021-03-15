package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Material
 */
@Entity(name = "material")
public class Material {

    @Id
    @GenericGenerator(name = "materialid", strategy = "com.soen390.team11.generator.MaterialIDGenerator")
    @GeneratedValue(generator = "materialid")
    private String materialid;

    @Column
    private String name;

    /**
     * default constructor
     */
    public Material() {

    }

    /**
     * the constructor with param
     * 
     * @param name the desired name for the new instance
     */
    public Material(String name) {
        this.name = name;
    }

    /**
     * the constructor with all params
     * 
     * @param materialid the given materialid for the new instance
     * @param name       the given name for the new instance
     */
    public Material(String materialid, String name) {
        this.materialid = materialid;
        this.name = name;
    }

    /**
     * getter method of the materialid
     * 
     * @return the materialid of the instance
     */
    public String getMaterialid() {
        return materialid;
    }

    /**
     * mutator method of the materialid
     * 
     * @param materialid the desired materialid of the instance
     */
    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    /**
     * getter method of the name
     * 
     * @return the name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * mutator method of the name
     * 
     * @param name the desired name of the instance
     */
    public void setName(String name) {
        this.name = name;
    }
}