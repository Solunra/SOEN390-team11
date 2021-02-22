package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;

public class Vendor {

    @Id
    @GenericGenerator(name="vendor_id", strategy = "com.soen390.team11.generator.VendorIDGenerator")
    @GeneratedValue(generator="vendor_id")
    public String vendorID;

    public String type;

    public String saleID;

    public Vendor() {
        // Empty Constructor
    }

    public Vendor(String vendorID, String type, String saleID) {
        this.vendorID = vendorID;
        this.type = type;
        this.saleID = saleID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }
}
