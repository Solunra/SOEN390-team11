package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for Vendors
 */
@Entity
public class Vendors {

    @Id
    @GenericGenerator(name="vendor_id", strategy = "com.soen390.team11.generator.VendorIDGenerator")
    @GeneratedValue(generator="vendor_id")
    private String vendorID;
    private String companyname;
    private String address;
    private String phone;
    private String email;

    public Vendors() {
        // Empty Constructor
    }

    public Vendors(String companyname, String address, String phone, String email) {
        this.companyname = companyname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }



    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
