package com.soen390.team11.dto;

import com.soen390.team11.constant.Status;

/**
 * DTO for productCustomer order, have detail about product and invoice number of purchase
 */
public class ProductCustomerOrderDto {
    private String productid;
    private String name;
    private String size;
    private String color;
    private String finish;
    private String grade;
    private float price;
    private Status status;
    private String invoiceid;

    public ProductCustomerOrderDto() {
    }

    public ProductCustomerOrderDto(String productid, String name, String size, String color, String finish, String grade, float price, Status status, String invoiceid) {
        this.productid = productid;
        this.name = name;
        this.size = size;
        this.color = color;
        this.finish = finish;
        this.grade = grade;
        this.price = price;
        this.status = status;
        this.invoiceid = invoiceid;
    }

    public String getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getFinish() {
        return finish;
    }

    public String getGrade() {
        return grade;
    }

    public float getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    @Override
    public String toString() {
        return "ProductCustomerOrderDto{" +
                "productid='" + productid + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", finish='" + finish + '\'' +
                ", grade='" + grade + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", invoiceid='" + invoiceid + '\'' +
                '}';
    }
}
