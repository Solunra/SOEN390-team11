package com.soen390.team11.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * product table is independ
 * it is the owner of the relation between product and variant
 * update the product would update the variant too
 * the relation product_variant will be create , it is many to many relationship
 */
@Entity(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long productid;
    @Column(unique = true)
    private String name;
    @Column
    private String type;
    @Column(unique = true)
    private String UUID;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="product_variant",
            joinColumns = {@JoinColumn(name = "productid")},
            inverseJoinColumns ={ @JoinColumn(name="variantid")}
    )
    private Set<Variant> variants = new HashSet<>();

    public Product() {
    }

    public Product(String name, String type) {
        this.name = name;
        this.type = type;

    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long id) {
        this.productid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UID) {
        this.UUID = UID;
    }

    public Set<Variant> getVariants() {
        return variants;
    }

    public void setVariants(Set<Variant> variantSet) {
        this.variants = variantSet;
    }
    public void addVariant(Variant variant){
        this.variants.add(variant);
    }



    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", UID='" + UUID + '\'' +
                ", variantSet=" + variants +
                '}';
    }
    // list of material
    // varient

}
