package com.soen390.team11.dto;


import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.RawMaterial;

import java.util.List;

public class RawMaterialRequestDto {
    private String name;
    private Long rawmaterialid;

    public RawMaterialRequestDto() {

    }

    public RawMaterialRequestDto(String name, Long rawmaterialid) {
        this.name = name;
        this.rawmaterialid = rawmaterialid;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Long getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(Long rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    public RawMaterial getMaterial(){
        return new RawMaterial(rawmaterialid, name);
    }
}
