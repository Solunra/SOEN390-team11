package com.soen390.team11.dto;

import com.soen390.team11.entity.Product;

public class ProductRequestDto {
    private String name;
    private String type;
    private String size;
    private String color;
    private String finish;
    private String grade;

    public ProductRequestDto() {
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
    public Product getProduct(){
        return new Product(name, type, size, color ,finish ,grade);
    }

}
