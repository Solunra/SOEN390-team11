package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Part
 */
@Entity(name="part")
public class Part {

    @Id
    @GenericGenerator(name="partid", strategy = "com.soen390.team11.generator.PartIDGenerator")
    @GeneratedValue(generator="partid")
    private String partid;

    @Column
    private String name;

    public Part() {

    }

    public Part(String partid, String name) {
        this.partid = partid;
        this.name = name;
    }

    public Part(String name) {
        this.partid = partid;
        this.name = name;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
