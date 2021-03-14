package com.soen390.team11.dto;

import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.RawMaterial;

import java.util.List;

public class RawMaterialRequestDto {
    private String name;
    private String rawmaterialid;
    private float cost;

    public RawMaterialRequestDto() {

    }

    public RawMaterialRequestDto(String name, String rawmaterialid, float cost) {
        this.name = name;
        this.rawmaterialid = rawmaterialid;
        this.cost = cost;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public RawMaterial getMaterial() {
        return new RawMaterial(rawmaterialid, name, cost);
    }
}
