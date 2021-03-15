package com.soen390.team11.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Database Entity for Material's Raw Materials
 */
@Entity(name="material_rawmaterials")
public class MaterialRawMaterials {

    @EmbeddedId
    private MaterialRawMaterialsId materialRawMaterialsId;

    public MaterialRawMaterials() {

    }

    public MaterialRawMaterials(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.materialRawMaterialsId = MaterialRawMaterialsId;
    }

    public MaterialRawMaterialsId getMaterialRawMaterialsId() {
        return materialRawMaterialsId;
    }

    public void setMaterialRawMaterialsId(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.materialRawMaterialsId = MaterialRawMaterialsId;
    }


}
