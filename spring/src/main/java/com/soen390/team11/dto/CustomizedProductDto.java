package com.soen390.team11.dto;

public class CustomizedProductDto {
    private String type;
    private String color;
    private String size;
    private String finish;

    /**
     * default constructor
     */
    public CustomizedProductDto() {
    }

    /**
     * constructor with all params
     * 
     * @param type   the desired type chosen by the customer
     * @param color  the desired color chosen by the customer
     * @param size   the desired size chosen by the customer
     * @param finish the desired finish chosen by the customer
     */
    public CustomizedProductDto(String type, String color, String size, String finish) {
        this.type = type;
        this.color = color;
        this.size = size;
        this.finish = finish;
    }

    /**
     * getter method for chosen type
     * 
     * @return the type chosen by the customer
     */
    public String getType() {
        return type;
    }

    /**
     * mutator method for customer to change the choice
     * 
     * @param type the desired type of the customer
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getter method for chosen color
     * 
     * @return the color chosen by the customer
     */
    public String getColor() {
        return color;
    }

    /**
     * mutator method for customer to change the choice
     * 
     * @param type the desired color of the customer
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * getter method for chosen size
     * 
     * @return the size chosen by the customer
     */
    public String getSize() {
        return size;
    }

    /**
     * mutator method for customer to change the choice
     * 
     * @param type the desired size of the customer
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * getter method for chosen finish
     * 
     * @return the finish chosen by the customer
     */
    public String getFinish() {
        return finish;
    }

    /**
     * mutator method for customer to change the choice
     * 
     * @param type the desired finish of the customer
     */
    public void setFinish(String finish) {
        this.finish = finish;
    }
}