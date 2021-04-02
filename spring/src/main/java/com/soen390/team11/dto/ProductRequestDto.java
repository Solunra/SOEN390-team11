package com.soen390.team11.dto;

import com.soen390.team11.constant.Status;
import com.soen390.team11.entity.Product;

/**
 * Product Request Body
 */
public class ProductRequestDto {
    // attributes for product
    private String name;
    private String type;
    private String size;
    private String color;
    private String finish;
    private String grade;
    private float cost;
    private float price;
    private Status status;

    // attributes for product_parts
    private String saddleId;
    private String frameId;
    private String wheelId;
    private String frontSetId;
    private String pedalUnitId;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name, String type, String size, String color, String finish, String grade,
            float cost, float price, String saddleId, String frameId, String wheelId, String frontSetId,
            String pedalUnitId) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
        this.cost = cost;
        this.price = price;
        this.saddleId = saddleId;
        this.frameId = frameId;
        this.wheelId = wheelId;
        this.frontSetId = frontSetId;
        this.pedalUnitId = pedalUnitId;
    }

    public ProductRequestDto(String name, String type, String size, String color, String finish, String grade, float cost, float price) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
        this.cost = cost;
        this.price = price;
    }

    public ProductRequestDto(String name, String type, String size, String color, String finish, String grade, float price, Status status) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
        this.cost = cost;
        this.price = price;
        this.status = status;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSaddleId() {
        return saddleId;
    }

    public String getFrameId() {
        return frameId;
    }

    public String getWheelId() {
        return wheelId;
    }

    public String getFrontSetId() {
        return frontSetId;
    }

    public String getPedalUnitId() {
        return pedalUnitId;
    }

    public Product getProduct() {
        return new Product(name, type, size, color, finish, grade, cost, price);
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", finish='" + finish + '\'' +
                ", grade='" + grade + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}