package com.soen390.team11.entity;

import javax.persistence.*;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String size;
    @Column
    private String color;
    @Column
    private String finish;
    @Column
    private String grade;

    public Product() {
    }

    public Product(String name, String type, String size, String color, String finish, String grade) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
