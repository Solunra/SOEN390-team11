package com.soen390.team11.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="material_rawmaterials")
public class MaterialRawMaterials {

    @EmbeddedId
    private MaterialRawMaterialsId MaterialRawMaterialsId;

    public MaterialRawMaterials() {

    }

    public MaterialRawMaterials(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.MaterialRawMaterialsId = MaterialRawMaterialsId;
    }

    public MaterialRawMaterialsId getMaterialRawMaterialsId() {
        return MaterialRawMaterialsId;
    }

    public void setMaterialRawMaterialsId(MaterialRawMaterialsId MaterialRawMaterialsId) {
        this.MaterialRawMaterialsId = MaterialRawMaterialsId;
    }


}
