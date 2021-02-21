package com.soen390.team11.dto;


import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.RawMaterial;

import java.util.List;

public class MaterialRequestDto {
    private String name;
    private Long materialid;

    public MaterialRequestDto() {

    }

    public MaterialRequestDto(String name, Long materialid) {
        this.name = name;
        this.materialid = materialid;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Long getmaterialid() {
        return materialid;
    }

    public void setmaterialid(Long materialid) {
        this.materialid = materialid;
    }

    public Material getMaterial(){
        return new Material(materialid, name);
    }
}
