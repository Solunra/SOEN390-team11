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

    @NotNull
    private Integer qty;

    /**
     * default constructor
     */
    public MaterialRawMaterialsId() {
    }

    /**
     * constructor with all params
     * 
     * @param materialid    the materialid for the new instance
     * @param rawmaterialid the rawmaterialid for the new instance
     * @param qty           the quantity of raw materials needed for the
     *                      corresponding material
     */
    public MaterialRawMaterialsId(String materialid, String rawmaterialid, Integer qty) {
        this.materialid = materialid;
        this.rawmaterialid = rawmaterialid;
        this.qty = qty;
    }

    /**
     * getter method for the materialid of the composited pk
     * 
     * @return the materialid of the cpk
     */
    public String getMaterialid() {
        return materialid;
    }

    /**
     * mutator method for the materialid of the cpk
     * 
     * @param materialid the desired materialid of the cpk
     */
    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    /**
     * getter method for the rawmaterialid of the composited pk
     * 
     * @return the rawmaterialid of the cpk
     */
    public String getRawmaterialid() {
        return rawmaterialid;
    }

    /**
     * mutator method for the rawmaterialid of the cpk
     * 
     * @param rawmaterialid the desired rawmaterialid of the cpk
     */
    public void setRawmaterialid(String rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    /**
     * getter method for the qty of the composited pk
     * 
     * @return the quantity of the cpk
     */
    public Integer getQty() {
        return qty;
    }

    /**
     * mutator method for the quantity of the cpk
     * 
     * @param qty the desired quantity of the cpk
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * check if two materialid are of the same data type and value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MaterialRawMaterialsId that = (MaterialRawMaterialsId) o;
        return Objects.equals(materialid, that.materialid) && Objects.equals(rawmaterialid, that.rawmaterialid);
    }

    /**
     * guarantee the stability of generated hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(materialid, rawmaterialid);
    }
}