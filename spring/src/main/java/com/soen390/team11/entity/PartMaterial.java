package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity(name = "part_material")
public class PartMaterial {

    @Id
    @GenericGenerator(name = "id", strategy = "com.soen390.team11.generator.PartMaterialIDGenerator")
    @GeneratedValue(generator = "id")
    private String id;

    @Column
    private String part_id;

    @Column
    private String material_id;

    @Column
    private Integer material_quantity;

    /**
     * default constructor
     */
    public PartMaterial() {

    }

    /**
     * constructor with an auto-generated id
     * 
     * @param part_id           the part_id of the instance
     * @param material_id       the material_id of the instance
     * @param material_quantity the material_quantity of the instance
     */
    public PartMaterial(String part_id, String material_id, Integer material_quantity) {
        this.part_id = part_id;
        this.material_id = material_id;
        this.material_quantity = material_quantity;
    }

    /**
     * cosntructor with all params
     * 
     * @param id                the id of the instance
     * @param part_id           the part_id of the instance
     * @param material_id       the material_id of the instance
     * @param material_quantity the material_quantity of the instance
     */
    public PartMaterial(String id, String part_id, String material_id, Integer material_quantity) {
        this.id = id;
        this.part_id = part_id;
        this.material_id = material_id;
        this.material_quantity = material_quantity;
    }

    /**
     * getter method for the id of partMaterial
     * 
     * @return the id of the instance
     */
    public String getId() {
        return id;
    }

    /**
     * getter method for the part_id of partMaterial
     * 
     * @return the partid of the instance
     */
    public String getPartId() {
        return part_id;
    }

    /**
     * getter method for the materialid of partMaterial
     * 
     * @return the materialid of the instance
     */
    public String getMaterialId() {
        return material_id;
    }

    /**
     * getter method for the material_quantity of partMaterial
     * 
     * @return the material_quantity of the instance
     */
    public Integer getmaterial_quantity() {
        return material_quantity;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param id the desired id of the instance
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param part_id the desired partid of the instance
     */
    public void setPartId(String part_id) {
        this.part_id = part_id;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param material_id the desired materialid of the instance
     */
    public void setMaterialId(String material_id) {
        this.material_id = material_id;
    }

    /**
     * mutator method for the partmaterial id
     * 
     * @param material_quantity the desired material_quantity of the instance
     */
    public void setmaterial_quantity(Integer material_quantity) {
        this.material_quantity = material_quantity;
    }
}