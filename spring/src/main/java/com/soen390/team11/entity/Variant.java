package com.soen390.team11.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantid;
    @Column
    private String variant;
    @Column
    private String value;
    @ManyToMany(mappedBy = "variants")
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public Variant() {
    }

    public Variant(String variant, String value) {
        this.variant = variant;
        this.value = value;
    }

    public Long getVariantid() {
        return variantid;
    }

    public void setVariantid(Long varientId) {
        this.variantid = varientId;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> productSet) {
        this.products = productSet;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "varientID=" + variantid +
                ", variant='" + variant + '\'' +
                ", value='" + value + '\'' +
                ", products=" + products +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant variant1 = (Variant) o;
        return Objects.equals(variant, variant1.variant) && Objects.equals(value, variant1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variant, value);
    }
}
