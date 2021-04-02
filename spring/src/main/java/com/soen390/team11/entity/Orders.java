package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

/**
 * Database Entity for Order
 */
@Entity
public class Orders {

    private String vendorID;

    private String saleID;

    @Id
    @GenericGenerator(name="order_id", strategy = "com.soen390.team11.generator.OrderIDGenerator")
    @GeneratedValue(generator="order_id")
    private String orderID;

    private int quantity;

    private OffsetDateTime time;
    private String userid;
    private OffsetDateTime ordertime;

    public Orders() {
    }

    public Orders(String vendorID, String saleID,int quantity, OffsetDateTime time) {
        this.vendorID = vendorID;
        this.saleID = saleID;
        this.quantity = quantity;
        this.time = time;
    }
    public Orders(String vendorID, String saleID, int quantity, OffsetDateTime time,OffsetDateTime ordertime, String userid) {
        this.vendorID = vendorID;
        this.saleID = saleID;
        this.quantity = quantity;
        this.time = time;
        this.ordertime = ordertime;
        this.userid = userid;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserid() {
        return userid;
    }

    public OffsetDateTime getOrdertime() {
        return ordertime;
    }
}
