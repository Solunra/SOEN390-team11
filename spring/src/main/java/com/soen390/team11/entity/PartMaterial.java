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
    @GenericGenerator(name = "id", strategy = "com.soen390.team11.generator.PartMaterialIDGenerator")
    @GeneratedValue(generator = "id")
    private String id;

    @Column
    private String partId;

    @Column
    private String materialId;

    @Column
    private Integer materialQuantity;

    /**
     * default constructor
     */
    public PartMaterial() {

    }

    /**
     * constructor with an auto-generated id
     * 
     * @param partId           the partId of the instance
     * @param materialId       the materialId of the instance
     * @param materialQuantity the materialQuantity of the instance
     */
    public PartMaterial(String partId, String materialId, Integer materialQuantity) {
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
    }

    /**
     * cosntructor with all params
     * 
     * @param id               the id of the instance
     * @param partId           the partId of the instance
     * @param materialId       the materialId of the instance
     * @param materialQuantity the materialQuantity of the instance
     */
    public PartMaterial(String id, String partId, String materialId, Integer materialQuantity) {
        this.id = id;
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
    }

    /**
     * getter method for the id of partMaterial
     * 
     * @return the id of the instance
     */
    public String getId() {
        return id;
    }

    /**
     * getter method for the partId of partMaterial
     * 
     * @return the partid of the instance
     */
    public String getPartId() {
        return partId;
    }

    /**
     * getter method for the materialid of partMaterial
     * 
     * @return the materialid of the instance
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * getter method for the materialQuantity of partMaterial
     * 
     * @return the materialQuantity of the instance
     */
    public Integer getmaterialQuantity() {
        return materialQuantity;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param id the desired id of the instance
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param partId the desired partid of the instance
     */
    public void setPartId(String partId) {
        this.partId = partId;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param materialId the desired materialid of the instance
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param materialQuantity the desired materialQuantity of the instance
     */
    public void setmaterialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
    }
}