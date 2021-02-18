package com.soen390.team11.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "part_inventory")
public class PartInventory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ElementCollection
    @MapKeyJoinColumn(name="material_id")
    @Column(name="material_quantity")
    private Map<Material, Integer> material_id_list = new HashMap<>();

    public PartInventory() {

    }

    public PartInventory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Material, Integer> getMaterials() {
        return material_id_list;
    }

    public void setMaterials(Map<Material, Integer> material_id_list) {
        this.material_id_list = material_id_list;
    }

}
