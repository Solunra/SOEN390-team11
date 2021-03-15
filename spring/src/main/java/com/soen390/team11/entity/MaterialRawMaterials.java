package com.soen390.team11.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Database Entity for Material's Raw Materials
 */
@Entity(name = "material_rawmaterials")
public class MaterialRawMaterials {

    @EmbeddedId
    private MaterialRawMaterialsId materialRawMaterialsId;

    /**
     * default constructor
     */
    public MaterialRawMaterials() {

    }

    /**
     * constructor with param
     * 
     * @param MaterialRawMaterialsId the desired embededid of the new instance
     */
    public MaterialRawMaterials(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.materialRawMaterialsId = MaterialRawMaterialsId;
    }

    /**
     * getter method for the materialid and rawmaterialid
     * 
     * @return the object containing materialid and rawmaterialid
     */
    public MaterialRawMaterialsId getMaterialRawMaterialsId() {
        return materialRawMaterialsId;
    }

    /**
     * mutator method for the materialid and rawmaterialid
     * 
     * @param MaterialRawMaterialsId the desired materialid and rawmaterialid of the
     *                               instance
     */
    public void setMaterialRawMaterialsId(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.materialRawMaterialsId = MaterialRawMaterialsId;
    }
}