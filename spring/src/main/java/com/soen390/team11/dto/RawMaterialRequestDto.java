package com.soen390.team11.dto;

import com.soen390.team11.entity.RawMaterial;

/**
 * Raw Material Request Body
 */
public class RawMaterialRequestDto {
    private String name;
    private String rawmaterialid;
    private String description;
    private double cost;
    private String unit;
    private String vendorID;
    private String companyname;

    public RawMaterialRequestDto() {

    }

    public RawMaterialRequestDto(String name, String rawmaterialid) {
        this.name = name;
        this.rawmaterialid = rawmaterialid;
    }

    public RawMaterialRequestDto(String name, String description, double cost, String unit, String vendorID) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.unit = unit;
        this.vendorID = vendorID;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(String rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public RawMaterial getMaterial() {
        return new RawMaterial(rawmaterialid, name);
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

}
