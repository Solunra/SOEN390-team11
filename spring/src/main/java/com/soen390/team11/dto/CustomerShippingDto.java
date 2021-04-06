package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;

/**
 * customer shipping DTO
 * the information of the customer shipping
 */
public class CustomerShippingDto extends CustomerDto {

    private String customerID;
    private String userID;

    public CustomerShippingDto() {
    }

    public CustomerShippingDto(String firstname, String lastname, String address, String city,
        String province, String zip, String country, String userID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.country = country;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public Customer getCustomer() {
        return new Customer(firstname, lastname, address, city, province, zip, country, userID);
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
