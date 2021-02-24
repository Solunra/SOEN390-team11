package com.soen390.team11.entity;

import com.soen390.team11.constant.Type;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vendors {

    @Id
    @GenericGenerator(name="vendor_id", strategy = "com.soen390.team11.generator.VendorIDGenerator")
    @GeneratedValue(generator="vendor_id")
    private String vendorID;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String saleID;

    public Vendors() {
        // Empty Constructor
    }

    public Vendors(Type type, String saleID) {
        this.type = type;
        this.saleID = saleID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }
}
