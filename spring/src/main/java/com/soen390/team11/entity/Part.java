package com.soen390.team11.entity;



import javax.persistence.*;
import java.util.List;

@Entity(name="part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partid;

    @Column
    private String name;

    public Part() {

    }

    public Part(Long partid, String name) {
        this.partid = partid;
        this.name = name;
    }

    public Long getPartid() {
        return partid;
    }

    public void setPartid(Long partid) {
        this.partid = partid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
