package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ProductMachinery {
    @Id
    @GenericGenerator(name="machineryId", strategy = "com.soen390.team11.generator.ProductMachineryIDGenerator")
    @GeneratedValue(generator="machineryId")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int timer;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductMachinery() {
    }

    public ProductMachinery(String name, String status, int timer, Product product) {
        this.name = name;
        this.status = status;
        this.timer = timer;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
