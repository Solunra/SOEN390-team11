package com.soen390.team11.entity;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialRawMaterialsId implements Serializable {

    @NotNull
    private long materialid;

    @NotNull
    private long rawmaterialid;

    public MaterialRawMaterialsId() {

    }

    public MaterialRawMaterialsId(long materialid, long rawmaterialid) {
        this.materialid = materialid;
        this.rawmaterialid = rawmaterialid;
    }

    public long getmaterialid() {
        return materialid;
    }

    public void setmaterialid(long materialid) {
        this.materialid = materialid;
    }

    public long getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(long rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialRawMaterialsId that = (MaterialRawMaterialsId) o;
        return ((materialid==(that.materialid)) && (rawmaterialid==(that.rawmaterialid)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialid, rawmaterialid);
    }
}
