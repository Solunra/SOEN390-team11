package com.soen390.team11.entity;



import javax.persistence.*;
import java.util.List;

@Entity(name="material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialid;

    @Column
    private String name;

    public Material() {

    }

    public Material(Long partid, String name) {
        this.partid = partid;
        this.name = name;
    }

    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
