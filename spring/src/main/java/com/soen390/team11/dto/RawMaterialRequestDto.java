package com.soen390.team11.dto;



import com.soen390.team11.entity.RawMaterial;

import java.util.List;

public class RawMaterialRequestDto {
    private String name;
    private String rawmaterialid;
    private String description;
    private double price;
    private  String unit;
    private String vendorID;
    private String companyname;

    public RawMaterialRequestDto() {

    }

    public RawMaterialRequestDto(String name, String rawmaterialid) {
        this.name = name;
        this.rawmaterialid = rawmaterialid;
    }

    public RawMaterialRequestDto(String name, String description, double price, String unit, String vendorID) {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public RawMaterial getMaterial(){
        return new RawMaterial(rawmaterialid, name);
    }
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


}
