package com.soen390.team11.dto;


import com.soen390.team11.entity.Material;

/**
 * Material's Expected Request Body
 */
public class MaterialRequestDto {
    private String name;

    public MaterialRequestDto() {

    }

    public MaterialRequestDto(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Material getMaterial(){
        return new Material(name);
    }
}
