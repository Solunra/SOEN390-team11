package com.soen390.team11.entity;



import javax.persistence.*;
import java.util.List;

@Entity(name="rawMaterial")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rawmaterialid;

    @Column
    private String name;

    public RawMaterial() {

    }

    public RawMaterial(Long rawmaterialid, String name) {
        this.rawmaterialid = rawmaterialid;
        this.name = name;
    }

    public Long getrawmaterialid() {
        return rawmaterialid;
    }

    public void setrawmaterialid(Long rawmaterialid) {
        this.rawmaterialid = rawmaterialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
