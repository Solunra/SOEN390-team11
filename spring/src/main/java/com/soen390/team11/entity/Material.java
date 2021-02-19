package com.soen390.team11.entity;



import javax.persistence.*;
import java.util.List;

@Entity(name="material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long materialid;

    @Column
    private String name;

    public Material() {

    }

    public Material(long materialid, String name) {
        this.materialid = materialid;
        this.name = name;
    }

    public long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(long materialid) {
        this.materialid = materialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
