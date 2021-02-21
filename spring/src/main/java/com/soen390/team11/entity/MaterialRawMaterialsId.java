package com.soen390.team11.entity;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialRawMaterialsId implements Serializable {

    @NotNull
    private Long materialid;

    @NotNull
    private Long rawmaterialid;

    public MaterialRawMaterialsId() {
    }

    public MaterialRawMaterialsId(Long materialid, Long rawmaterialid) {
        this.materialid = materialid;
        this.rawmaterialid = rawmaterialid;
    }

    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }

    public Long getRawmaterialid() {
        return rawmaterialid;
    }

    public void setRawmaterialid(Long rawmaterialid) {
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
