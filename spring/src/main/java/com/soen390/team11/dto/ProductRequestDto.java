package com.soen390.team11.dto;

import com.soen390.team11.entity.Product;

import java.util.ArrayList;
import java.util.UUID;

public class ProductRequestDto {

    private String name;
    private String type;
    private ArrayList<String> color;
    private ArrayList<String> size;
    private ArrayList<String> finish;
    private ArrayList<String> grade;

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

    public ArrayList<String> getColor() {
        return color;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public ArrayList<String> getSize() {
        return size;
    }

    public void setSize(ArrayList<String> size) {
        this.size = size;
    }

    public ArrayList<String> getFinish() {
        return finish;
    }

    public void setFinish(ArrayList<String> finish) {
        this.finish = finish;
    }

    public ArrayList<String> getGrade() {
        return grade;
    }

    public void setGrade(ArrayList<String> grade) {
        this.grade = grade;
    }

    public Product getProduct(){
        Product product = new Product(name, type);
        product.setUUID(UUID.randomUUID().toString());
        return product;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color=" + color.toString() +
                ", size=" + size.toString() +
                ", finish=" + finish.toString() +
                ", grade=" + grade.toString() +
                '}';
    }
}
