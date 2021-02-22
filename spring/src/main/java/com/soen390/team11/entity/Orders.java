package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Orders {

    public String vendorID;

    @Id
    @GenericGenerator(name="order_id", strategy = "com.soen390.team11.generator.OrderIDGenerator")
    @GeneratedValue(generator="order_id")
    public String orderID;

    public int quantity;

    public OffsetDateTime time;

    public Orders() {
    }

    public Orders(String vendorID, int quantity, OffsetDateTime time) {
        this.vendorID = vendorID;
        this.quantity = quantity;
        this.time = time;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
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
}
