package com.soen390.team11.entity;



import javax.persistence.*;
import java.util.List;

@Entity(name="part")
public class Part {

    @Id
    private String name;

    public Part() {

    }

    public Part(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
