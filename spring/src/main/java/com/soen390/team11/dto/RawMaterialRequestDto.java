package com.soen390.team11.dto;


import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.RawMaterial;

import java.util.List;

public class RawMaterialRequestDto {
    private String name;
    private long rawmaterialid;

    public RawMaterialRequestDto() {

    }

    public RawMaterialRequestDto(String name, long rawmaterialid) {
        this.name = name;
        this.rawmaterialid = rawmaterialid;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public long getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(long rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    public RawMaterial getMaterial(){
        return new RawMaterial(rawmaterialid, name);
    }
}
