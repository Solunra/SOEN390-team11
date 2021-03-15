package com.soen390.team11.entity;

import com.sun.istack.NotNull;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Database Object for Materials ID and Raw Material ID
 */
@Embeddable
public class MaterialRawMaterialsId implements Serializable {

    @NotNull
    private String materialid;

    @NotNull
    private String rawmaterialid;

    public MaterialRawMaterialsId() {
    }

    public MaterialRawMaterialsId(String materialid, String rawmaterialid) {
        this.materialid = materialid;
        this.rawmaterialid = rawmaterialid;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getRawmaterialid() {
        return rawmaterialid;
    }

    public void setRawmaterialid(String rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialRawMaterialsId that = (MaterialRawMaterialsId) o;
        return Objects.equals(materialid, that.materialid) && Objects.equals(rawmaterialid, that.rawmaterialid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialid, rawmaterialid);
    }
}
