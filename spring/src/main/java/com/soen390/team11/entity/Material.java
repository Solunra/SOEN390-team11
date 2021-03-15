package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Material
 */
@Entity(name="material")
public class Material {

    @Id
    @GenericGenerator(name="materialid", strategy = "com.soen390.team11.generator.MaterialIDGenerator")
    @GeneratedValue(generator="materialid")
    private String materialid;

    @Column
    private String name;

    public Material() {

    }

    public Material(String name){
        this.name = name;
    }

    public Material(String materialid, String name) {
        this.materialid = materialid;
        this.name = name;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
