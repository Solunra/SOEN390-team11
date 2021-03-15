package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Product
 */
@Entity(name = "product")
public class Product {

    @Id
    @GenericGenerator(name = "productid", strategy = "com.soen390.team11.generator.ProductIDGenerator")
    @GeneratedValue(generator = "productid")
    private String productid;
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
    @Column
    private float cost;
    @Column
    private float price;

    /**
     * defalut constructor for product
     */
    public Product() {
    }

    /**
     * Constructor with all params
     * 
     * @param name   the name of the newly created product
     * @param type   the type of the newly created product
     * @param size   the size of the newly created product
     * @param color  the color of the newly created product
     * @param finish the finish of the newly created product
     * @param grade  the grade of the newly created product
     * @param cost   the cost of the newly created product
     * @param price  the price of the newly created product
     */
    public Product(String name, String type, String size, String color, String finish, String grade, float cost,
            float price) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
        this.cost = cost;
        this.price = price;
    }

    /**
     * getter method for productid
     * 
     * @return the productid of the instance
     */
    public String getProductid() {
        return productid;
    }

    /**
     * mutator method for the product productid
     * 
     * @param productid the desired productid of this instance
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * getter method for product name
     * 
     * @return the name of the instance
     */
    public String getName() {
        return name;
    }

    /**
     * mutator method for the product name
     * 
     * @param name the desired name of this instance
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method for the product type
     * 
     * @return the type of the instance
     */
    public String getType() {
        return type;
    }

    /**
     * mutator method for the product type
     * 
     * @param type the desired type of this instance
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getter method for the product size
     * 
     * @return the size of the product
     */
    public String getSize() {
        return size;
    }

    /**
     * mutator method for the product size
     * 
     * @param size the desired size of this instance
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * getter method for the product color
     * 
     * @return the color of the instance
     */
    public String getColor() {
        return color;
    }

    /**
     * mutator method for the product color
     * 
     * @param color the desired color of this instance
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * getter method for the product finish
     * 
     * @return the finish of the instance
     */
    public String getFinish() {
        return finish;
    }

    /**
     * mutator method for the product finish
     * 
     * @param finish the desired finish of this instance
     */
    public void setFinish(String finish) {
        this.finish = finish;
    }

    /**
     * getter method for the product grade
     * 
     * @return the grade of the instance
     */
    public String getGrade() {
        return grade;
    }

    /**
     * mutator method for the product grade
     * 
     * @param grade the desired grade of this instance
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * getter method for the product cost
     * 
     * @return the cost of the instance
     */
    public float getCost() {
        return cost;
    }

    /**
     * mutator method for the product cost
     * 
     * @param cost the desired cost of this instance
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * getter method for the product price (3*cost)
     * 
     * @return the price of the instance
     */
    public float getPrice() {
        return price;
    }

    /**
     * mutator method for the product price
     * 
     * @param price the desired price of this instance
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
