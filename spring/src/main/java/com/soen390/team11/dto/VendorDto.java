package com.soen390.team11.dto;

/**
 * Vendor Request Body
 */
public class VendorDto {

    private String vendorID;
    private String companyName;
    private String address;
    private String phone;
    private String email;

    public VendorDto() {

    }

    public VendorDto( String companyName, String address, String phone, String email) {
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public VendorDto(String vendorID, String companyName, String address, String phone, String email) {
        this.vendorID = vendorID;
        this.companyName = companyName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
