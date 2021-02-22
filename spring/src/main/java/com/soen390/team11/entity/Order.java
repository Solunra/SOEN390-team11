package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import java.time.OffsetDateTime;

public class Order {

    public String vendorID;

    @Id
    @GenericGenerator(name="order_id", strategy = "com.soen390.team11.generator.OrderIDGenerator")
    @GeneratedValue(generator="order_id")
    public String orderID;

    public OffsetDateTime time;

    public Order() {
    }

    public Order(String vendorID, String orderID, OffsetDateTime time) {
        this.vendorID = vendorID;
        this.orderID = orderID;
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
}
